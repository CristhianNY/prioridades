package com.cristhianbonilla.data.source.remote.home

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel

interface MagazineRemoteSource: RemoteSource {
    suspend fun getMagazineList(year: String?, search: String?): Result<Failure, MagazineModel>
    suspend fun getMagazinePdf(magazineId:String): Result<Failure, MagazinePdfModel>
    suspend fun getKeyWordsList(): Result<Failure, KeyWordModel>
}