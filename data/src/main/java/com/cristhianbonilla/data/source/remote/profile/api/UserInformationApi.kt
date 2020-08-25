package com.cristhianbonilla.data.source.remote.profile.api

import com.cristhianbonilla.data.entity.magazinepdf.MagazinePdfEntity
import com.cristhianbonilla.data.entity.profile.UserEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInformationApi {

    @GET("jwt-api/get-user.php")
    suspend fun getUserInformation(): Response<UserEntity>
}