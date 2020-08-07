package com.cristhianbonilla.domain.usecase.magazinepdf

import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.cristhianbonilla.domain.usecase.UseCase

interface GetMagazinePdfUseCase : UseCase<GetMagazinePdfUseCase.Params, MagazinePdfModel>{

    data class Params(val magazineId:String)
}