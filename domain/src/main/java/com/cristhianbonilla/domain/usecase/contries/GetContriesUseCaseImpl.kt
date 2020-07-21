package com.cristhianbonilla.domain.usecase.contries

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.repository.countries.CountryRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class GetContriesUseCaseImpl(private val repository:CountryRepository) : GetcountryUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): Result<Failure, CountryModel> {
        return repository.getCountriesList()
    }
}