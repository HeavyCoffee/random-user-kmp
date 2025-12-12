package com.heavycoffee.core.backendapi.domain.usecase

import com.heavycoffee.core.backendapi.domain.datasource.RandomUserDataSource
import com.heavycoffee.core.backendapi.domain.model.RandomUserParams
import com.heavycoffee.core.backendapi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class GenerateUserUseCase {
    abstract suspend operator fun invoke(params: RandomUserParams): Result<User>
}

internal class GenerateUserUseCaseImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val randomUserDataSource: RandomUserDataSource
) : GenerateUserUseCase() {
    override suspend fun invoke(
        params: RandomUserParams
    ): Result<User> = withContext(dispatcher) {
        runCatching { randomUserDataSource.generateUser(params) }
    }
}