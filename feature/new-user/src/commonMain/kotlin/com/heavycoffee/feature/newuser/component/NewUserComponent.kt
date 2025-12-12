package com.heavycoffee.feature.newuser.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.core.backendapi.domain.model.RandomUserParams
import com.heavycoffee.core.backendapi.domain.model.User
import com.heavycoffee.core.backendapi.domain.usecase.GenerateUserUseCase
import com.heavycoffee.core.backendapi.domain.usecase.GetGenderListUseCase
import com.heavycoffee.core.backendapi.domain.usecase.GetNationalityListUseCase
import com.heavycoffee.core.database.domain.usecase.IsUserListNotEmptyUseCase
import com.heavycoffee.core.database.domain.usecase.SaveUserUseCase
import com.heavycoffee.core.feature.decompose.ext.componentScope
import com.heavycoffee.core.feature.decompose.ext.state
import com.heavycoffee.core.uikit.component.Item
import com.heavycoffee.feature.newuser.component.NewUserComponent.Effect
import com.heavycoffee.feature.newuser.component.NewUserComponent.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import com.heavycoffee.core.database.domain.model.User as UserDb

@Immutable
interface NewUserComponent {
    val state: StateFlow<State>
    val effect: Flow<Effect>
    fun onGenderChange(gender: Item<RandomUserParams.Gender>)
    fun onNationalityChange(nationality: Item<RandomUserParams.Nationality>)
    fun onGenerateClick()
    fun onBackClick()

    @Immutable
    data class State(
        val selectedGender: Item<RandomUserParams.Gender> = Item(
            name = RandomUserParams.Gender.Male.name,
            data = RandomUserParams.Gender.Male
        ),
        val selectedNationality: Item<RandomUserParams.Nationality> = Item(
            name = RandomUserParams.Nationality.US.code,
            data = RandomUserParams.Nationality.US
        ),
        val isLoading: Boolean = false,
        val isStartComponent: Boolean = false,
        val genders: List<Item<RandomUserParams.Gender>> = emptyList(),
        val nationalities: List<Item<RandomUserParams.Nationality>> = emptyList()
    )

    sealed interface Effect {
        data object ShowError : Effect
    }
}

internal class NewUserComponentPreviewProvider : PreviewParameterProvider<NewUserComponent> {
    override val values: Sequence<NewUserComponent>
        get() = sequenceOf(
            object : NewUserComponent {
                override val state: StateFlow<State>
                    get() = MutableStateFlow(State())

                override val effect: Flow<Effect>
                    get() = emptyFlow()

                override fun onGenderChange(gender: Item<RandomUserParams.Gender>) = Unit
                override fun onNationalityChange(nationality: Item<RandomUserParams.Nationality>) = Unit
                override fun onGenerateClick() = Unit
                override fun onBackClick() = Unit
            }
        )
}

internal class NewUserComponentImpl(
    componentContext: ComponentContext,
    private val generateUserUseCase: GenerateUserUseCase,
    private val isUserListNotEmptyUseCase: IsUserListNotEmptyUseCase,
    private val getGenderListUseCase: GetGenderListUseCase,
    private val getNationalityListUseCase: GetNationalityListUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val onBack: () -> Unit,
    private val onOpenUserList: () -> Unit
) : NewUserComponent, ComponentContext by componentContext {
    private val stateContainer = state<State, Effect>(State())
    override val state: StateFlow<State> = stateContainer.state
    override val effect: Flow<Effect> = stateContainer.effect

    init {
        stateContainer.intent {
            componentScope.launch(Dispatchers.Default) {
                val isUserListNotEmpty = isUserListNotEmptyUseCase().getOrDefault(false)
                val genders = getGenderListUseCase().map {
                    Item(
                        data = it,
                        name = it.name
                    )
                }
                val nationalities = getNationalityListUseCase().map {
                    Item(
                        data = it,
                        name = it.code
                    )
                }
                stateContainer.reduce {
                    copy(
                        isStartComponent = !isUserListNotEmpty,
                        selectedGender = genders.first(),
                        selectedNationality = nationalities.first(),
                        genders = genders,
                        nationalities = nationalities
                    )
                }
            }
        }
    }

    override fun onGenderChange(
        gender: Item<RandomUserParams.Gender>
    ) = stateContainer.reduce {
        copy(selectedGender = gender)
    }

    override fun onNationalityChange(
        nationality: Item<RandomUserParams.Nationality>
    ) = stateContainer.reduce {
        copy(selectedNationality = nationality)
    }

    override fun onGenerateClick() = stateContainer.reduce {
        if (isLoading) return@reduce this

        copy(isLoading = true).also {
            componentScope.launch(Dispatchers.Default) {
                runCatching {
                    val user = generateUserUseCase(
                        RandomUserParams(
                            gender = selectedGender.data,
                            nationality = listOf(selectedNationality.data)
                        )
                    ).getOrThrow()

                    saveUserUseCase(user.toUserDb()).getOrThrow()
                }
                    .onSuccess {
                        stateContainer.intent {
                            if (isStartComponent) onOpenUserList() else onBack()
                        }
                    }.onFailure {
                        stateContainer.reduce {
                            stateContainer.postEffect(Effect.ShowError)
                            copy(isLoading = false)
                        }
                    }
            }
        }
    }

    override fun onBackClick() {
        onBack()
    }
}

private fun User.toUserDb() = UserDb(
    id = -1,
    gender = gender,
    name = UserDb.Name(
        title = name.title,
        first = name.first,
        last = name.last
    ),
    location = UserDb.Location(
        street = UserDb.Location.Street(
            number = location.street.number,
            name = location.street.name
        ),
        city = location.city,
        state = location.state,
        country = location.country,
        postcode = location.postcode,
        coordinates = UserDb.Location.Coordinates(
            latitude = location.coordinates.latitude,
            longitude = location.coordinates.longitude
        ),
        timezone = UserDb.Location.Timezone(
            offset = location.timezone.offset,
            description = location.timezone.description
        )
    ),
    email = email,
    login = UserDb.Login(
        uuid = login.uuid,
        username = login.username,
        password = login.password,
        salt = login.salt,
        md5 = login.md5,
        sha1 = login.sha1,
        sha256 = login.sha256
    ),
    dob = UserDb.DateOfBirth(
        date = dob.date,
        age = dob.age
    ),
    registered = UserDb.Registered(
        date = registered.date,
        age = registered.age
    ),
    phone = phone,
    cell = cell,
    idDocument = UserDb.IdDocument(
        name = id.name,
        value = id.value
    ),
    picture = UserDb.Picture(
        large = picture.large,
        medium = picture.medium,
        thumbnail = picture.thumbnail
    ),
    nat = nat
)