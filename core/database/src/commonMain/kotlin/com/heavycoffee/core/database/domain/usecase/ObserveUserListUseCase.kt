package com.heavycoffee.core.database.domain.usecase

import com.heavycoffee.core.database.domain.datasource.UserDataSource
import com.heavycoffee.core.database.domain.model.User
import kotlinx.coroutines.flow.Flow

abstract class ObserveUserListUseCase {
    abstract suspend operator fun invoke(): Flow<List<User>>
}

internal class ObserveUserListUseCaseUseCaseImpl(
    private val userDataSource: UserDataSource
) : ObserveUserListUseCase() {
    override suspend fun invoke(): Flow<List<User>> {
        return userDataSource.getUsersFlow()
    }
}