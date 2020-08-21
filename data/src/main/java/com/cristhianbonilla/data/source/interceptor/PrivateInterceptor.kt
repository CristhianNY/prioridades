package com.cristhianbonilla.data.source.interceptor

import com.cristhianbonilla.data.main.NetworkException
import com.cristhianbonilla.data.main.NetworkStatus
import com.cristhianbonilla.data.source.local.LocalHandler
import com.cristhianbonilla.domain.session.Session
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection.*

class PrivateInterceptor(
    private val session: Session,
    private val networkStatus: NetworkStatus,
    private val localHandler: LocalHandler
) : Interceptor {

    companion object {
        private const val HEADER_NAME_TOKEN = "Authorization"
        private const val HEADER_NAME_ACCEPT = "Accept"
        private const val HEADER_NAME_CONTENT = "Content-Type"
        private const val HEADER_VALUE_JSON = "application/json; charset=utf-8"
        private const val HEADER_NAME_PROGRESSID = "progressId"
    }

    @Throws(NetworkException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStatus.isConnected()) {
            throw NetworkException
        }
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (session.isLogged()) {
            requestBuilder
                .addHeader("Authorization","Bearer "+session.getAccessToken())
        }

        var response = chain.proceed(requestBuilder.build())
        when (response.code) {
            HTTP_OK -> {
                //TODO Invalidate caches
//                localHandler.invalidateCaches()
            }
            HTTP_UNAUTHORIZED -> {
                GlobalScope.launch(IO) { session.logout() }
            }
            HTTP_FORBIDDEN -> {

            }
            HTTP_PRECON_FAILED -> {
                //TODO One session for channel
            }
        }
        return response
    }
}