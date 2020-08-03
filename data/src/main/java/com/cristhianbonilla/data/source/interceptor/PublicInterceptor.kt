package com.cristhianbonilla.data.source.interceptor

import com.cristhianbonilla.domain.session.Session
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class PublicInterceptor(private val session: Session) : Authenticator {

    override fun authenticate(
        route: Route?,
        response: Response
    ): Request? =
        when (runBlocking { session.refresh() }) {
            true -> response.request.newBuilder().build()
            false -> {
                runBlocking { session.logout() }
                null
            }
        }
}