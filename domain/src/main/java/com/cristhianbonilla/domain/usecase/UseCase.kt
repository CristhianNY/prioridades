package com.cristhianbonilla.domain.usecase

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


interface UseCase<in Params, out Type> where Type : Any {

    suspend operator fun invoke(
        params: Params,
        context: CoroutineContext = IO
    ): Result<Failure, Type> =
        withContext(context) {
            invoke(params, context)
        }

    object None
}