package com.heavycoffee.feature.userlist.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.arkivanov.decompose.ComponentContext
import com.heavycoffee.core.database.domain.model.User
import com.heavycoffee.core.database.domain.usecase.ObserveUserListUseCase
import com.heavycoffee.core.feature.decompose.ext.componentScope
import com.heavycoffee.core.feature.decompose.ext.state
import com.heavycoffee.feature.userlist.component.UserListComponent.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Immutable
interface UserListComponent {
    val state: StateFlow<State>

    fun onAddNewUserClick()
    fun onUserItemClick(id: Long)

    @Immutable
    data class State(
        val users: List<User> = emptyList()
    )
}

internal class UserListComponentPreviewProvider : PreviewParameterProvider<UserListComponent> {
    override val values: Sequence<UserListComponent>
        get() = sequenceOf(
            object : UserListComponent {
                override val state: StateFlow<State>
                    get() = MutableStateFlow(
                        State(
                            users = listOf(
                                User(
                                    id = 1,
                                    gender = "Male",
                                    name = User.Name(
                                        title = "Mister",
                                        first = "Ivan",
                                        last = "Ivanov"
                                    ),
                                    location = User.Location(
                                        street = User.Location.Street(
                                            number = 8929,
                                            name = "Lenina"
                                        ),
                                        city = "Moscow",
                                        state = "Moscow",
                                        country = "Russia",
                                        postcode = "63104",
                                        coordinates = User.Location.Coordinates(
                                            latitude = "-69.8246",
                                            longitude = "134.8719"
                                        ),
                                        timezone = User.Location.Timezone(
                                            offset = "+7",
                                            description = "Adelaide, Darwin"
                                        )
                                    ),
                                    email = "test@test.com",
                                    login = User.Login(
                                        uuid = "7a0eed16-9430-4d68-901f-c0d4c1c3bf00",
                                        username = "username",
                                        password = "password",
                                        salt = "salt",
                                        md5 = "md5",
                                        sha1 = "sha1",
                                        sha256 = "sha256"
                                    ),
                                    dob = User.DateOfBirth(
                                        date = "date",
                                        age = 100
                                    ),
                                    registered = User.Registered(
                                        date = "date",
                                        age = 101
                                    ),
                                    phone = "(272) 790-0888",
                                    cell = "(489) 330-2385",
                                    idDocument = User.IdDocument(
                                        name = "SSN",
                                        value = "405-88-3636"
                                    ),
                                    picture = User.Picture(
                                        large = "",
                                        medium = "",
                                        thumbnail = ""
                                    ),
                                    nat = "RU",
                                )
                            )
                        )
                    )

                override fun onAddNewUserClick() = Unit
                override fun onUserItemClick(id: Long) = Unit
            }
        )
}

internal class UserListComponentImpl(
    componentContext: ComponentContext,
    private val observeUserListUseCase: ObserveUserListUseCase,
    private val onOpenUserInfo: (id: Long) -> Unit,
    private val onAddNewUser: () -> Unit
) : UserListComponent, ComponentContext by componentContext {
    private val stateContainer = state<State, Nothing>(State())
    override val state: StateFlow<State> = stateContainer.state

    init {
        stateContainer.intent {
            componentScope.launch(Dispatchers.Default) {
                observeUserListUseCase().collect { users ->
                    stateContainer.reduce { copy(users = users) }
                }
            }
        }
    }

    override fun onAddNewUserClick() {
        onAddNewUser()
    }

    override fun onUserItemClick(id: Long) {
        onOpenUserInfo(id)
    }
}