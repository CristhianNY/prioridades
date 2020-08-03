package com.cristhianbonilla.domain.usecase.home

import com.cristhianbonilla.domain.repository.home.MagazineRepository
import kotlin.coroutines.CoroutineContext

class GetMagazineListUseCaseImpl(private val magazineRepository: MagazineRepository) :
    GetMagazineListUseCase {

    override suspend fun invoke(
        params: GetMagazineListUseCase.Params,
        context: CoroutineContext
    ) = magazineRepository.getMagazineList(params.year, params.search)
}