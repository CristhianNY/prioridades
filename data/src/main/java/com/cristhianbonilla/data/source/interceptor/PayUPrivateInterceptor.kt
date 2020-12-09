package com.cristhianbonilla.data.source.interceptor

import com.cristhianbonilla.data.main.NetworkException
import com.cristhianbonilla.data.main.NetworkStatus
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection.*

class PayUPrivateInterceptor(
    private val networkStatus: NetworkStatus
) : Interceptor {

    @Throws(NetworkException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStatus.isConnected()) {
            throw NetworkException
        }
        val request = chain.request()

        val newUrl = request.url.newBuilder().build()
        val requestBuilder = request.newBuilder().url(newUrl).method(request.method, request.body)
            .header("Content-Type", "application/json").header("Accept","application/json")
            .build()
        var response = chain.proceed(requestBuilder)

        when (response.code) {
            HTTP_OK -> {
                //TODO Invalidate caches
//                localHandler.invalidateCaches()
            }
            HTTP_UNAUTHORIZED -> {
                //     GlobalScope.launch(IO) { session.logout() }
            }
            HTTP_FORBIDDEN -> {

            }
            HTTP_PRECON_FAILED -> {
                //TODO One session for channel
            }
            TOKEN_NOT_SEND -> {
                // GlobalScope.launch(IO) { session.logout() }
            }

            TOKEN_EXPIRED -> {
                //   GlobalScope.launch(IO) { session.logout() }
            }
        }
        return response
    }

    companion object {
        private const val TOKEN_NOT_SEND = 701
        private const val TOKEN_EXPIRED = 302
    }

}