package com.cristhianbonilla.data.source.remote.home.api

import com.cristhianbonilla.data.entity.home.MagazineEntity
import retrofit2.Response

interface MagazineListApi {
    suspend fun getMagazineList(year:String?, search:String?): Response<MagazineEntity>
}