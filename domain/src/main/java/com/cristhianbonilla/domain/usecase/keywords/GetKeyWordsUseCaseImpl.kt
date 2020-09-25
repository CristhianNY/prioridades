package com.cristhianbonilla.domain.usecase.keywords

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.repository.home.MagazineRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class GetKeyWordsUseCaseImpl(private val repository: MagazineRepository) : GetKeyWordsUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): CustomResult<Failure, KeyWordModel> {
        return repository.getKeyWords()
    }
}