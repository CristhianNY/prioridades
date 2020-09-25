package com.cristhianbonilla.data.source.remote.source

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.exception.Failure.RemoteError
import com.cristhianbonilla.domain.functional.CustomResult
import retrofit2.Response

interface RemoteSource {

    suspend fun <E, M> request(
        call: suspend () -> Response<E>,
        handleSuccess: (E, Int) -> M,
        handleError: (Int) -> Failure = { RemoteError() }
    ): CustomResult<Failure, M> =
        try {
            val response = call()

            when(response.code()){
                SESSION_EXPIRED ->{
                    CustomResult.Error(Failure.SessionExpired)
                }

                SESSION_EXPIRED2 ->{
                    CustomResult.Error(Failure.SessionExpired)
                }
                SUBSCRIPTION_NOT_ACTIVATED ->{
                    CustomResult.Error(Failure.SubscriptionNotActivated)
                }

                USER_EXIST ->{
                    CustomResult.Error(Failure.UserAlreadyExist)
                }

                else ->{
                    val responseBody: E = response.body()!!

                    CustomResult.Success(handleSuccess(responseBody, response.code()))
                }
            }
        } catch (exception: Exception) {
            exception.toRemoteError(handleError)
        }

    fun <E, M> requestSync(
        call: () -> E,
        handleSuccess: (E) -> M,
        handleError: (Int) -> Failure = { RemoteError() }
    ): CustomResult<Failure, M> =
        try {
            val response = call()
            CustomResult.Success(handleSuccess(response))
        } catch (exception: Exception) {
            exception.toRemoteError(handleError)
        }

companion object{
    const val SESSION_EXPIRED = 301
    const val SESSION_EXPIRED2 = 302
    const val SUBSCRIPTION_NOT_ACTIVATED = 700
    const val USER_EXIST = 703
}
}