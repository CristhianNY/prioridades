package com.cristhianbonilla.revistaprioridades.di.domain.home

import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCase
import com.cristhianbonilla.domain.usecase.home.GetMagazineListUseCaseImpl
import org.koin.dsl.module

internal val magazineListDomainModule = module {
    factory<GetMagazineListUseCase> {
        GetMagazineListUseCaseImpl(magazineRepository = get())
    }
}