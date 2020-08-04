package com.cristhianbonilla.data.source.remote.home.api

import com.cristhianbonilla.data.entity.home.MagazineEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MagazineListApi {
    @GET("apis/revistas.php")
    suspend fun getMagazineList(@Query("year") year:String?, @Query("search") search:String?): Response<MagazineEntity>
}