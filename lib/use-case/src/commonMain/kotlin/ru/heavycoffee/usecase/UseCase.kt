package ru.heavycoffee.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

//abstract class UseCase<in P, out R>(
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
//) {
//    // you should normally use this to call usecase
//    suspend operator fun invoke(param: P): Result<R> = executeWithResult(param)
//
//    protected abstract suspend fun executeInternal(param: P): R
//
//    private suspend fun executeWithResult(
//        param: P,
//        dispatcher: CoroutineDispatcher = Dispatchers.IO
//    ): Result<R> {
//        return withContext(dispatcher) {
//            try {
//                Result.success(executeInternal(param))
//            } catch (e: CancellationException) {
//                throw e
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//    }
//}

abstract class UseCase<in P, out R>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(params: P): Result<R> = withContext(dispatcher) {
        try {
            Result.success( execute(params))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    protected abstract suspend fun execute(params: P): R
}