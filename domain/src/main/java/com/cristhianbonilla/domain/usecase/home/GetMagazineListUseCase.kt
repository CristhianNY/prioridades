package com.cristhianbonilla.domain.usecase.home

import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase.Params

interface GetMagazineListUseCase : UseCase<Params, MagazineModel>{

    data class Params(val year: String,val search:String)
}