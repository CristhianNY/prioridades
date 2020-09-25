package com.cristhianbonilla.domain.repository.countries

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.countries.CountryModel

interface CountryRepository {

    suspend fun getCountriesList():CustomResult<Failure,CountryModel>
}