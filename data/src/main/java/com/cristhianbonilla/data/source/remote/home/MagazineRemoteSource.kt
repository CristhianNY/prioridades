package com.cristhianbonilla.data.source.remote.home

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.functional.Result

interface MagazineRemoteSource: RemoteSource {
    suspend fun getMagazineList(year: String?, search: String?): Result<Failure, MagazineModel>
}