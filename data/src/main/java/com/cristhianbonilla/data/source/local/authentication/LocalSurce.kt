package com.cristhianbonilla.data.source.local.authentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.functional.CustomResult.Error

interface LocalSource {
    fun invalidate()
    suspend fun <M> doAction(
        call: suspend () -> M
    ): CustomResult<Failure, M> =
        try {
            CustomResult.Success(call())
        } catch (exception: Exception) {
            Error(Failure.LocalError)
        }
}