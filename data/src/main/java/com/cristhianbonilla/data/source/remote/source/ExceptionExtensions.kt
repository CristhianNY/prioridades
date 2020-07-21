package com.cristhianbonilla.data.source.remote.source

import com.cristhianbonilla.data.main.NetworkException
import com.cristhianbonilla.data.reporter.reporter
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.exception.RemoteTypeError
import com.cristhianbonilla.domain.functional.Result
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.security.InvalidParameterException

internal fun Exception.toRemoteError(handleError: (Int) -> Failure): Result.Error<Failure> {
    reporter.exception(this)
    return Result.Error(
        when (this) {
            is NetworkException -> Failure.NetworkConnection
            is InvalidParameterException -> Failure.RemoteError(
                RemoteTypeError.INVALID_PARAMETER_REQUEST,
                this.message
            )
            is JsonDataException -> Failure.RemoteError(RemoteTypeError.INVALID_PARSER_ENTITY, this.message)
            is HttpException ->
                when (val code = this.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> Failure.SessionExpired
                    else -> handleError(code)
                }
            else -> Failure.RemoteError(RemoteTypeError.GENERIC)
        }
    )
}