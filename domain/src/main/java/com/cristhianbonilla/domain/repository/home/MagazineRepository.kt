package com.cristhianbonilla.domain.repository.home

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.home.MagazineModel

interface MagazineRepository {

   suspend fun getMagazineList(year: String,search: String): Result<Failure, MagazineModel>
}