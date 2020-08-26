package com.cristhianbonilla.data.source.remote.authenication.login.api

import com.cristhianbonilla.data.entity.authenticatin.AuthEntity
import com.cristhianbonilla.data.entity.authenticatin.LoginEntity
import com.cristhianbonilla.data.entity.magazinepdf.MagazinePdfEntity
import com.cristhianbonilla.data.entity.profile.UserEntity
import retrofit2.Response
import retrofit2.http.*

interface LoginApi {

    companion object {
        private const val ACCESS_TOKEN_HEADER = "accessToken"
        const val SIGNED_REQUEST_HEADER = "X-Signed-Request"
    }

    @POST("/jwt-api/")
    suspend fun postLogin(
        @HeaderMap headers: Map<String, String> = emptyMap(), @Body body: AuthEntity
    ): Response<LoginEntity>


    @GET("jwt-api/register-user.php")
    suspend fun registerUser(
        @Query("email") email: String?,
        @Query("nombres") names: String?,
        @Query("apellidos") lastNames: String?,
        @Query("pais") country: String?,
        @Query("ciudad") city: String?,
        @Query("telefono") phone: String?,
        @Query("clave") password: String?
    ): Response<UserEntity>
}