package com.cristhianbonilla.data.source.requestBuilder

import com.cristhianbonilla.data.entity.authenticatin.LogoutRequest
import com.cristhianbonilla.data.entity.authenticatin.RequestLogin
import com.cristhianbonilla.data.entity.authenticatin.RequestRefresh

class AuthRequestBodyBuilderImpl : AuthenticationRequestBodyBuilder {

    override fun createLogin(
        username: String,
        password: String
    ) = RequestLogin(
        username, password
    )

    override fun createLogout(accessToken: String): LogoutRequest =
        LogoutRequest(accessToken)

    override fun createRefresh(
        document: String,
        refreshToken: String
    ): RequestRefresh =
        RequestRefresh(document, refreshToken)
}