package com.cristhianbonilla.revistaprioridades.di.domain.keywords

import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCaseImpl
import org.koin.dsl.module

internal val keywordsDomainModule = module {
    factory<GetKeyWordsUseCase> {
        GetKeyWordsUseCaseImpl(repository = get())
    }
}