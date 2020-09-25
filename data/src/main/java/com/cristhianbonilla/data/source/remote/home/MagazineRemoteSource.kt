package com.cristhianbonilla.data.source.remote.home

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel

interface MagazineRemoteSource: RemoteSource {
    suspend fun getMagazineList(year: String?, search: String?): CustomResult<Failure, MagazineModel>
    suspend fun getMagazinePdf(magazineId:String): CustomResult<Failure, MagazinePdfModel>
    suspend fun getKeyWordsList(): CustomResult<Failure, KeyWordModel>
}