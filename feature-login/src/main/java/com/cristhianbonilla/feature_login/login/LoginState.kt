package com.cristhianbonilla.feature_login.login

import com.cristhianbonilla.foundations.base.BaseState

open class LoginState : BaseState {
    object ErrorLogin : LoginState()
    object LoadingLogin : LoginState()
    object SuccessLogin : LoginState()
}