package com.cristhianbonilla.prioridades.di.data.countries

import com.cristhianbonilla.data.repository.authentication.CountriesRepositoryImpl
import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSource
import com.cristhianbonilla.data.source.local.countries.CountriesRemoteSourceImpl
import org.koin.dsl.module

internal val countryDataModule = module {
    single<CountriesRemoteSource> {
        CountriesRemoteSourceImpl(assetJsonReader = get())
    }

    single<CountriesRepositoryImpl> { CountriesRepositoryImpl(countriesRemoteSource = get()) }
}