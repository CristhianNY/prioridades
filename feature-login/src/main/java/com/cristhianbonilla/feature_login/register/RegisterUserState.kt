package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.AuthenticationState

sealed class RegisterUserState : AuthenticationState() {
    object Error : RegisterUserState()
    object Loading : RegisterUserState()
    object Success : RegisterUserState()
    object UserAlreadyExist : RegisterUserState()
    object NavigateToTermsAndConditions : RegisterUserState()
    data class NavigateToRegisterStep2(
        val names: String,
        val lastNames: String,
        val email: String,
        val password: String
    ) : RegisterUserState()

    object NavigateToLogin : RegisterUserState()
    object UserRegistrationSuccess : RegisterUserState()
}