package com.cristhianbonilla.data.repository.authentication

import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.repository.countries.CountryRepository

class CountriesRepositoryImpl(private val countriesRemoteSource: CountriesRemoteSource ) :
    CountryRepository {
    override suspend fun getCountriesList(): Result<Failure, CountryModel> {
        return countriesRemoteSource.getCountries()
    }
}