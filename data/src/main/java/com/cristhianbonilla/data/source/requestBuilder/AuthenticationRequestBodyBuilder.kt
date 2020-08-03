package com.cristhianbonilla.data.source.requestBuilder

import com.cristhianbonilla.data.entity.authenticatin.LogoutRequest
import com.cristhianbonilla.data.entity.authenticatin.RequestLogin
import com.cristhianbonilla.data.entity.authenticatin.RequestRefresh

interface AuthenticationRequestBodyBuilder {


    fun createLogin(
        username: String,
        password: String
    ): RequestLogin


    fun createRefresh(
        document: String,
        refreshToken: String
    ): RequestRefresh

    fun createLogout(
        accessToken: String
    ): LogoutRequest
}