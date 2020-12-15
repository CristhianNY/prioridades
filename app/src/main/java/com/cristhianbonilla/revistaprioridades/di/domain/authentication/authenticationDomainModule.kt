package com.cristhianbonilla.revistaprioridades.di.domain.authentication

import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCaseImpl
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatus
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatusImpl
import com.cristhianbonilla.domain.usecase.register.DoRegisterUseCase
import com.cristhianbonilla.domain.usecase.register.DoRegisterUseCaseImpl
import org.koin.dsl.module

internal val authenticationDomainModule = module {
    factory<DoLoginUseCase> { DoLoginUseCaseImpl(authRepository = get()) }
    factory<GetLoginStatus> { GetLoginStatusImpl(authRepository = get()) }
    factory<DoRegisterUseCase> { DoRegisterUseCaseImpl(authRepository = get())}
}