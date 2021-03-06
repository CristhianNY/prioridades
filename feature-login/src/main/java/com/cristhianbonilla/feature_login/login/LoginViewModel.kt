package com.cristhianbonilla.feature_login.login

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCase
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatus
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.execute

class LoginViewModel(
    scope: Scope,
    data: LoginData,
    tracker: LoginTracker,
    private val doLoginUseCase: DoLoginUseCase,
    private val getLoginStatus: GetLoginStatus
) : BaseViewModel<LoginState, LoginData, LoginTracker>(scope, data, tracker) {

    fun doLogin() {
        tracker.viewDisplayed()
        data.loading()
        execute {
            doLoginUseCase(
                DoLoginUseCase.Params(
                    data.username.value,
                    data.password.value
                )
            ).fold(::handleLoginError, ::handleLoginSuccess)
        }
    }

    fun goToForgotPassword(){
        data.updateStateToForgotPassword()
    }

    private fun handleLoginSuccess(none: UseCase.None) {
        tracker.viewDisplayed()
        data.userAlreadyLogged()
    }

    fun goToRegisterFragment(){
        data.registerState()
    }

    private fun handleLoginError(failure: Failure) {
        tracker.viewDisplayed()
        data.error()
    }
}
