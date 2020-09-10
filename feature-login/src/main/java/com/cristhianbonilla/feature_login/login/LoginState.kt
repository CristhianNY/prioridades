package com.cristhianbonilla.feature_login.login

import com.cristhianbonilla.feature_login.AuthenticationState

sealed class LoginState : AuthenticationState() {
    object ErrorLogin : LoginState()
    object LoadingLogin : LoginState()
    object SuccessLogin : LoginState()
    object UserAlreadyLogged : LoginState()
    object RegisterUser : LoginState()
    object ForgotPassWord : LoginState()
}