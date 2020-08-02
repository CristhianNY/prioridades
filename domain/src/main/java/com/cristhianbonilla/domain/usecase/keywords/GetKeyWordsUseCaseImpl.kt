package com.cristhianbonilla.domain.usecase.keywords

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.repository.keywords.KeyWordsRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class GetKeyWordsUseCaseImpl(private val repository: KeyWordsRepository) : GetKeyWordsUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): Result<Failure, KeyWordModel> {
        return repository.getKeyWordsList()
    }
}