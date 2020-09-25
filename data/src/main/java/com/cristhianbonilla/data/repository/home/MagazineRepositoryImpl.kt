package com.cristhianbonilla.data.repository.home

import com.cristhianbonilla.data.source.remote.home.MagazineRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.cristhianbonilla.domain.repository.home.MagazineRepository

class MagazineRepositoryImpl(private val magazineRemoteSource: MagazineRemoteSource) :
    MagazineRepository {

    override suspend fun getMagazineList(
        year: String,
        search: String
    ): CustomResult<Failure, MagazineModel> {

        return magazineRemoteSource.getMagazineList(year, search)
    }

    override suspend fun getMagazinePdf(magazineId: String): CustomResult<Failure, MagazinePdfModel> {
        return magazineRemoteSource.getMagazinePdf(magazineId)
    }

    override suspend fun getKeyWords(): CustomResult<Failure, KeyWordModel> {
       return magazineRemoteSource.getKeyWordsList()
    }
}