package com.cristhianbonilla.data.source.remote.authenication.api

import com.cristhianbonilla.data.entity.authenticatin.LoginEntity
import com.cristhianbonilla.data.entity.authenticatin.LogoutRequest
import com.cristhianbonilla.data.entity.authenticatin.RequestLogin
import com.cristhianbonilla.data.entity.authenticatin.RequestRefresh
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthenticationApi {
    companion object{
        private const val ACCESS_TOKEN_HEADER = "accessToken"
        const val SIGNED_REQUEST_HEADER = "X-Signed-Request"
    }

    @POST("/mb-api-auth/authentication/login")
    suspend fun postLogin(@Body body: RequestLogin): Response<LoginEntity>


    @POST("/mb-api-auth/authentication/refresh-token")
    suspend fun postLoginRefresh(
        @Header(ACCESS_TOKEN_HEADER) accessToken: String,
        @Body body: RequestRefresh
    ): Response<LoginEntity>

    @POST("/mb-api-auth/authentication/logout")
    suspend fun postLogout(
        @Header(ACCESS_TOKEN_HEADER) accessToken: String,
        @Body body: LogoutRequest
    ): Response<ResponseBody>
}