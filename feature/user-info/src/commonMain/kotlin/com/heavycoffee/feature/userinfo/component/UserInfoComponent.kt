package com.heavycoffee.feature.userinfo.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.core.database.domain.usecase.GetUserByIdUseCase
import com.heavycoffee.core.feature.decompose.ext.componentScope
import com.heavycoffee.core.feature.decompose.ext.state
import com.heavycoffee.feature.userinfo.component.UserInfoComponent.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Immutable
interface UserInfoComponent {
    val state: StateFlow<State>

    fun onTabClick(tab: State.UserInfoTabEnum)

    fun onBackClick()

    @Immutable
    data class State(
        val selectedTab: UserInfoTabEnum = UserInfoTabEnum.MAIN_INFO,
        val firstName: String = "",
        val lastName: String = "",
        val gender: String = "",
        val age: String = "",
        val daeOfBirth: String = "",
        val phone: String = "",
        val cell: String = "",
        val email: String = "",
        val street: String = "", // Valwood Pkwy, 89
        val city: String = "",
        val state: String = "",
        val postcode: String = "",
        val timezone: String = "", // +9:30, Adelaide, Darwin
        val userAvatarUrl: String = "",
        val isError: Boolean = false
    ) {
        enum class UserInfoTabEnum {
            MAIN_INFO, PHONES, EMAIL, LOCATION
        }
    }
}

internal class UserInfoComponentPreviewProvider : PreviewParameterProvider<UserInfoComponent> {
    override val values: Sequence<UserInfoComponent>
        get() = sequenceOf(
            object : UserInfoComponent {
                override val state: StateFlow<State>
                    get() = MutableStateFlow(State(isError = false))

                override fun onTabClick(tab: State.UserInfoTabEnum) = Unit

                override fun onBackClick() = Unit
            }
        )
}

internal class UserInfoComponentImpl(
    id: Long,
    componentContext: ComponentContext,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val onBack: () -> Unit
) : UserInfoComponent, ComponentContext by componentContext {
    private val stateContainer = state<State, Nothing>(State())
    override val state: StateFlow<State> = stateContainer.state

    init {
        stateContainer.intent {
            componentScope.launch(Dispatchers.Default) {
                getUserByIdUseCase(id)
                    .onSuccess {
                        stateContainer.reduce {
                            copy(
                                firstName = it.name.first,
                                lastName = it.name.last,
                                gender = it.gender,
                                age = it.dob.age.toString(),
                                daeOfBirth = it.dob.date,
                                phone = it.phone,
                                cell = it.cell,
                                email = it.email,
                                street = it.location.street.fullInfo, // Valwood Pkwy, 89
                                city = it.location.city,
                                state = it.location.state,
                                postcode = it.location.postcode,
                                timezone = it.location.timezone.fullInfo, // +9:30, Adelaide, Darwin
                                userAvatarUrl = it.picture.medium
                            )
                        }
                    }
                    .onFailure {
                        stateContainer.reduce { copy(isError = false)}
                    }
            }
        }
    }

    override fun onTabClick(tab: State.UserInfoTabEnum) = stateContainer.reduce {
        copy(selectedTab = tab)
    }

    override fun onBackClick() {
        onBack()
    }
}