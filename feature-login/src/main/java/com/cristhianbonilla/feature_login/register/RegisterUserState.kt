package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.AuthenticationState

sealed class RegisterUserState : AuthenticationState() {
    object Error : RegisterUserState()
    object Loading : RegisterUserState()
    object Success : RegisterUserState()
    object UserAlreadyExist : RegisterUserState()
    object DrawerItemClicked : RegisterUserState()
    object NavigateToRegisterStep2 : RegisterUserState()
    data class NavigateToAccountTransactions(val accountId: String) : RegisterUserState()
    data class NavigateToDeposits(val depositId: String) : RegisterUserState()
    object NavigateToPreferences : RegisterUserState()
}