package com.cristhianbonilla.data.repository.countries

import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.repository.countries.CountryRepository

class CountriesRepositoryImpl(private val countriesRemoteSource: CountriesRemoteSource ) :
    CountryRepository {
    override suspend fun getCountriesList(): CustomResult<Failure, CountryModel> {
        return countriesRemoteSource.getCountries()
    }
}