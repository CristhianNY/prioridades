package com.cristhianbonilla.data.source.local.authentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.functional.Result.Error

interface LocalSource {
    fun invalidate()
    suspend fun <M> doAction(
        call: suspend () -> M
    ): Result<Failure, M> =
        try {
            Result.Success(call())
        } catch (exception: Exception) {
            Error(Failure.LocalError)
        }
}