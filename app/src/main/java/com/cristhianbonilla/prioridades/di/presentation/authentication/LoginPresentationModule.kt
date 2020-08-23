package com.cristhianbonilla.prioridades.di.presentation.authentication

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.feature_login.AuthenticationActivity
import com.cristhianbonilla.feature_login.login.LoginData
import com.cristhianbonilla.feature_login.login.LoginFragment
import com.cristhianbonilla.feature_login.login.LoginTracker
import com.cristhianbonilla.feature_login.login.LoginViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import com.cristhianbonilla.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val loginPresentationModule = module {

    scope(named<AuthenticationActivity>()) {
    }
    viewModel<MainViewModel> {
        MainViewModel(
            getLoginStatus = get()
        )
    }
    scope(named<LoginFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            LoginViewModel(
                scope = get(),
                data = LoginData(),
                tracker = LoginTracker(),
                doLoginUseCase = get(),
                getLoginStatus = get()
            )
        }
    }
}