package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.RegisterState

sealed class RegisterUserState : RegisterState() {
    object Error : RegisterUserState()
    object Loading : RegisterUserState()
    object Success : RegisterUserState()
    object DrawerItemClicked : RegisterUserState()
    object NavigateToProfile : RegisterUserState()
    data class NavigateToAccountTransactions(val accountId: String) : RegisterUserState()
    data class NavigateToDeposits(val depositId: String) : RegisterUserState()
    object NavigateToPreferences : RegisterUserState()
}