package com.cristhianbonilla.data.source.remote.profile.api

import com.cristhianbonilla.data.entity.profile.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface UserInformationApi {

    @GET("jwt-api/get-user.php")
    suspend fun getUserInformation(): Response<UserEntity>
    @GET("jwt-api/activar-usuario.php")
    suspend fun activateUser(): Response<UserEntity>


}