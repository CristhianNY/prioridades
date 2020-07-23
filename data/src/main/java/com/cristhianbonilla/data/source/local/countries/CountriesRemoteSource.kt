package com.cristhianbonilla.data.source.local.countries

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel

interface CountriesRemoteSource : RemoteSource {
    suspend fun getCountries():Result<Failure, CountryModel>
}