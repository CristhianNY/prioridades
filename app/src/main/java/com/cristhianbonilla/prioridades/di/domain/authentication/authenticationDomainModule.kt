package com.cristhianbonilla.prioridades.di.domain.authentication

import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCaseImpl
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatus
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatusImpl
import org.koin.dsl.module

internal val authenticationDomainModule = module {
    factory<DoLoginUseCase> { DoLoginUseCaseImpl(authRepository = get()) }
    factory<GetLoginStatus> { GetLoginStatusImpl(authRepository = get()) }
}