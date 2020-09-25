package com.cristhianbonilla.data.source.remote.home

import com.cristhianbonilla.data.entity.home.toModel
import com.cristhianbonilla.data.entity.keywords.toModel
import com.cristhianbonilla.data.entity.magazinepdf.toModel
import com.cristhianbonilla.data.source.remote.home.api.MagazineListApi
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel

class MagazineRemoteSourceImpl(private val magazineAPi: MagazineListApi) : MagazineRemoteSource {
    override suspend fun getMagazineList(
        year: String?,
        search: String?
    ): CustomResult<Failure, MagazineModel> {
        return request({
            magazineAPi.getMagazineList(
                year, search
            )
        }, { entity, _ -> entity.toModel() })
    }

    override suspend fun getMagazinePdf(magazineId: String): CustomResult<Failure, MagazinePdfModel> {
        return request({
            magazineAPi.getMagazinePdf(
                magazineId
            )
        }, { entity, _ -> entity.toModel() })
    }

    override suspend fun getKeyWordsList(): CustomResult<Failure, KeyWordModel> {
        return request({
            magazineAPi.getKeyWords(
            )
        }, { entity, _ -> entity.toModel() })
    }
}