package com.cristhianbonilla.prioridades.di.presentation.register

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.feature_login.AuthenticationActivity
import com.cristhianbonilla.feature_login.register.*
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named

internal val registerUserModule = module {
    scope(named<AuthenticationActivity>()) {
    }

    scope(named<RegisterFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            RegisterViewModel(
                scope = get(),
                data = RegisterData(),
                tracker = get(),
                getCountriesUseCase = get(),
                doRegisterUseCase = get()
            )
        }
        scoped { RegisterTracker() }
    }

    scope(named<FinishRegisterFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            RegisterViewModel(
                scope = get(),
                data = RegisterData(),
                tracker = get(),
                getCountriesUseCase = get(),
                doRegisterUseCase = get()
            )
        }
        scoped { RegisterTracker() }
    }
}
