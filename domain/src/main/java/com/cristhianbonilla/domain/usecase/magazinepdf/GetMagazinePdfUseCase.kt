package com.cristhianbonilla.domain.usecase.magazinepdf

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

interface GetMagazinePdfUseCase : UseCase<GetMagazinePdfUseCase.Params, MagazinePdfModel>{
    data class Params(val magazineId:String)
}