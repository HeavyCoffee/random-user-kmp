package com.heavycoffee.core.database.domain.usecase

import com.heavycoffee.core.database.domain.datasource.UserDataSource
import com.heavycoffee.core.database.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class SaveUserUseCase {
    abstract suspend operator fun invoke(user: User): Result<Unit>
}

internal class SaveUserUseCaseImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val userDataSource: UserDataSource
) : SaveUserUseCase() {
    override suspend fun invoke(user: User): Result<Unit> = withContext(dispatcher) {
        runCatching { userDataSource.addUser(user) }
    }
}