package com.heavycoffee.core.database.domain.usecase

import com.heavycoffee.core.database.domain.datasource.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class IsUserListNotEmptyUseCase {
    abstract suspend operator fun invoke(): Result<Boolean>
}

internal class IsUserListNotEmptyUseCaseImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val userDataSource: UserDataSource
) : IsUserListNotEmptyUseCase() {
    override suspend fun invoke(): Result<Boolean> {
        return withContext(dispatcher) {
            runCatching { userDataSource.count() > 0 }
        }
    }
}