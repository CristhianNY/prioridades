package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase.None
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.execute

class RegisterViewModel(
    scope: Scope,
    data: RegisterData,
    tracker: RegisterTracker,
    private val getCountriesUseCase: GetcountryUseCase
) : BaseViewModel<RegisterUserState,RegisterData,RegisterTracker>(scope,data,tracker) {

    fun getCountries(){
        data.loading()
        execute { getCountriesUseCase(None).fold(
            {
                data.error()
            }, ::handleGetCountries
        ) }
    }

    private fun handleGetCountries(countries: CountryModel){

        data.submitCountries(countries)
    }

}