package com.cristhianbonilla.prioridades.di.domain.profile

import com.cristhianbonilla.domain.usecase.authtentication.DoLogoutUseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLogoutUseCaseImpl
import com.cristhianbonilla.domain.usecase.profile.GetUserInformationUseCase
import com.cristhianbonilla.domain.usecase.profile.GetUserInformationUseCaseImpl
import org.koin.dsl.module

internal val profileDomainModule = module {
    factory<GetUserInformationUseCase> {
        GetUserInformationUseCaseImpl(repository = get())
    }
    factory<DoLogoutUseCase> {
        DoLogoutUseCaseImpl(authRepository = get())
    }
}