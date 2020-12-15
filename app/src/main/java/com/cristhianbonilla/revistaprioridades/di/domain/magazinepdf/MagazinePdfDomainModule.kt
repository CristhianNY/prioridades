package com.cristhianbonilla.revistaprioridades.di.domain.magazinepdf

import com.cristhianbonilla.domain.usecase.magazinepdf.GetMagazinePdfUseCase
import com.cristhianbonilla.domain.usecase.magazinepdf.GetMagazinePdfUseCaseImpl
import org.koin.dsl.module

internal val magazinePdfDomainModule = module {
    factory<GetMagazinePdfUseCase> {
        GetMagazinePdfUseCaseImpl(magazineRepository = get())
    }
}