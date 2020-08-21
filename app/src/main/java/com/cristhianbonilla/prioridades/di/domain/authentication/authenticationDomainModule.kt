package com.cristhianbonilla.prioridades.di.domain.authentication

import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCaseImpl
import org.koin.dsl.module

internal val authenticationDomainModule = module {
    factory<DoLoginUseCase> { DoLoginUseCaseImpl(authRepository = get()) }
}