package com.cristhianbonilla.data.source.remote.authenication.login.api

import com.cristhianbonilla.data.entity.authenticatin.AuthEntity
import com.cristhianbonilla.data.entity.authenticatin.LoginEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface LoginApi {

    companion object {
        private const val ACCESS_TOKEN_HEADER = "accessToken"
        const val SIGNED_REQUEST_HEADER = "X-Signed-Request"
    }

    @POST("/jwt-api/")
    suspend fun postLogin(
        @HeaderMap headers: Map<String, String> = emptyMap(), @Body body: AuthEntity
    ): Response<LoginEntity>
}