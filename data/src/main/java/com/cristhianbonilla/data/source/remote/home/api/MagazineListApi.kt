package com.cristhianbonilla.data.source.remote.home.api

import com.cristhianbonilla.data.entity.countries.CountryEntity
import com.cristhianbonilla.data.entity.home.MagazineEntity
import com.cristhianbonilla.data.entity.keywords.KeyWordEntity
import com.cristhianbonilla.data.entity.magazinepdf.MagazinePdfEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MagazineListApi {
    @GET("apis/revistas.php")
    suspend fun getMagazineList(
        @Query("year") year: String?,
        @Query("search") search: String?
    ): Response<MagazineEntity>


    @GET("jwt-api/revistas-leer.php")
    suspend fun getMagazinePdf(@Query("idRevista") magazineId: String?): Response<MagazinePdfEntity>

    @GET("jwt-api/get-keywords.php")
    suspend fun getKeyWords(): Response<KeyWordEntity>
}