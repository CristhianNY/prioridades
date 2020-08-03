package com.cristhianbonilla.data.source.remote.home

import com.cristhianbonilla.data.entity.home.toModel
import com.cristhianbonilla.data.source.remote.home.api.MagazineListApi
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.home.MagazineModel

class MagazineRemoteSourceImpl(private val magazineAPi: MagazineListApi) : MagazineRemoteSource {
    override suspend fun getMagazineList(
        year: String?,
        search: String?
    ): Result<Failure, MagazineModel> {
        return request({
            magazineAPi.getMagazineList(
                year, search
            )
        }, { entity, _ -> entity.toModel() })
    }
}