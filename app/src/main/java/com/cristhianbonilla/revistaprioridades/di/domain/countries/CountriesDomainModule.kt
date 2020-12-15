package com.cristhianbonilla.revistaprioridades.di.domain.countries

import com.cristhianbonilla.domain.usecase.contries.GetContriesUseCaseImpl
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import org.koin.dsl.module

internal val countriesDomainModule = module {
    factory<GetcountryUseCase> {
        GetContriesUseCaseImpl(repository = get())
    }
}