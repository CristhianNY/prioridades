package com.cristhianbonilla.prioridades.di.data.countries

import com.cristhianbonilla.data.repository.countries.CountriesRepositoryImpl
import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSource
import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSourceImpl
import com.cristhianbonilla.domain.repository.countries.CountryRepository
import org.koin.dsl.module

internal val countryDataModule = module {
    single<CountriesRemoteSource> {
        CountriesRemoteSourceImpl(assetJsonReader = get())
    }
    single<CountryRepository> { CountriesRepositoryImpl(countriesRemoteSource = get()) }
}