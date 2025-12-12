package ru.heavycoffee.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class FlowUseCase<in P, R>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    // you should normally use this to call usecase
    suspend operator fun invoke(param: P): Flow<Result<R>> = executeHandled(param)

    protected abstract suspend fun executeInternal(param: P): Flow<Result<R>>

    private suspend fun executeHandled(param: P): Flow<Result<R>> {
        return withContext(dispatcher) {
            try {
                executeInternal(param)
                    .catch { e ->
                        emit(Result.failure(e))
                    }
            } catch (e: Exception) {
                flow {
                    emit(
                        Result.failure(
                            SubscribeFlowUseCaseException(
                                message = e.message,
                                cause = e.cause
                            )
                        )
                    )
                }
            }
        }
    }

    class SubscribeFlowUseCaseException(
        override val message: String?,
        override val cause: Throwable?
    ) : Exception()
}