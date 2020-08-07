package com.cristhianbonilla.domain.repository.home

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel

interface MagazineRepository {

   suspend fun getMagazineList(year: String,search: String): Result<Failure, MagazineModel>
   suspend fun getMagazinePdf(magazineId: String): Result<Failure, MagazinePdfModel>
}