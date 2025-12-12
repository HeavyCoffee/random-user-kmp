package com.heavycoffee.core.database.domain.usecase

import com.heavycoffee.core.database.domain.datasource.UserDataSource
import com.heavycoffee.core.database.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class GetUserByIdUseCase {
    abstract suspend operator fun invoke(id: Long): Result<User>
}

internal class GetUserByIdUseCaseImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val userDataSource: UserDataSource
) : GetUserByIdUseCase() {
    override suspend fun invoke(id: Long): Result<User> {
        return withContext(dispatcher) {
            runCatching { userDataSource.getUserById(id) ?: throw Exception("User not found") }
        }
    }
}