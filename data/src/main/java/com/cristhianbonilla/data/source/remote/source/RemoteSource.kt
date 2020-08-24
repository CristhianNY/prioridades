package com.cristhianbonilla.data.source.remote.source

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.exception.Failure.RemoteError
import com.cristhianbonilla.domain.functional.Result
import retrofit2.Response

interface RemoteSource {

    suspend fun <E, M> request(
        call: suspend () -> Response<E>,
        handleSuccess: (E, Int) -> M,
        handleError: (Int) -> Failure = { RemoteError() }
    ): Result<Failure, M> =
        try {
            val response = call()

            when(response.code()){
                SESSION_EXPIRED ->{
                    Result.Error(Failure.SessionExpired)
                }
                SUBSCRIPTION_NOT_ACTIVATED ->{
                    Result.Error(Failure.SubscriptionNotActivated)
                }
                else ->{
                    val responseBody: E = response.body()!!

                    Result.Success(handleSuccess(responseBody, response.code()))
                }
            }
        } catch (exception: Exception) {
            exception.toRemoteError(handleError)
        }

    fun <E, M> requestSync(
        call: () -> E,
        handleSuccess: (E) -> M,
        handleError: (Int) -> Failure = { RemoteError() }
    ): Result<Failure, M> =
        try {
            val response = call()
            Result.Success(handleSuccess(response))
        } catch (exception: Exception) {
            exception.toRemoteError(handleError)
        }

companion object{
    const val SESSION_EXPIRED = 301
    const val SUBSCRIPTION_NOT_ACTIVATED = 700
}
}