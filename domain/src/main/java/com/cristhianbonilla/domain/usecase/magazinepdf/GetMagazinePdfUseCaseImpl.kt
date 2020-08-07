package com.cristhianbonilla.domain.usecase.magazinepdf

import com.cristhianbonilla.domain.repository.home.MagazineRepository
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import kotlin.coroutines.CoroutineContext

class GetMagazinePdfUseCaseImpl(private val magazineRepository: MagazineRepository) :
    GetMagazinePdfUseCase {

    override suspend fun invoke(
        params: GetMagazinePdfUseCase.Params,
        context: CoroutineContext
    ) = magazineRepository.getMagazinePdf(params.magazineId)
}