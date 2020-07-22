package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel

class RegisterViewModel(
    scope: Scope,
    data: RegisterData,
    tracker: RegisterTracker,
    private val getCountries: GetcountryUseCase
) : BaseViewModel<RegisterUserState,RegisterData,RegisterTracker>(scope,data,tracker) {

}