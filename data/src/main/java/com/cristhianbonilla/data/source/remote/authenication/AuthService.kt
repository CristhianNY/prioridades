package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.entity.authenticatin.toModel
import com.cristhianbonilla.data.source.remote.authenication.login.api.LoginApi
import com.cristhianbonilla.data.source.remote.authenication.login.api.LoginApi.Companion.SIGNED_REQUEST_HEADER

class AuthService(
    private val api: LoginApi,
    private val authRequestBodyBuilder: RequestBodyBuilder
) : AuthRemoteSource {

    override suspend fun postLogin(
        username: String,
        password: String,
        signedRequest: String?
    ) = request(
        {
            val headers = if (signedRequest == null) {
                emptyMap()
            } else {
                mapOf(SIGNED_REQUEST_HEADER to signedRequest)
            }
            var body = authRequestBodyBuilder.createLogin(username, password)
            api.postLogin(
                headers,
                body
            )
        },
        { loginEntity, _ -> loginEntity.toModel() }
    )
}