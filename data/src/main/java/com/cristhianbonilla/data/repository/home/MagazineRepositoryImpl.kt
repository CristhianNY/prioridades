package com.cristhianbonilla.data.repository.home

import com.cristhianbonilla.data.source.remote.home.MagazineRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.cristhianbonilla.domain.repository.home.MagazineRepository

class MagazineRepositoryImpl(private val magazineRemoteSource: MagazineRemoteSource) :
    MagazineRepository {

    override suspend fun getMagazineList(
        year: String,
        search: String
    ): Result<Failure, MagazineModel> {

        return magazineRemoteSource.getMagazineList(year, search)
    }

    override suspend fun getMagazinePdf(magazineId: String): Result<Failure, MagazinePdfModel> {
        return magazineRemoteSource.getMagazinePdf(magazineId)
    }
}