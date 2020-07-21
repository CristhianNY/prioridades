package com.cristhianbonilla.domain.repository.countries

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel

interface CountryRepository {

    suspend fun getCountriesList():Result<Failure,CountryModel>
}