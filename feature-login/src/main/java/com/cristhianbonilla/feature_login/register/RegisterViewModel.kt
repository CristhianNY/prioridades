package com.cristhianbonilla.feature_login.register

import android.widget.Toast
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase.None
import com.cristhianbonilla.domain.usecase.authtentication.DoLoginUseCase
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.domain.usecase.register.DoRegisterUseCase
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute

class RegisterViewModel(
    scope: Scope,
    data: RegisterData,
    tracker: RegisterTracker,
    private val getCountriesUseCase: GetcountryUseCase,
    private val doRegisterUseCase: DoRegisterUseCase
) : BaseViewModel<RegisterUserState, RegisterData, RegisterTracker>(scope, data, tracker) {

    fun getCountries() {
        execute {
            getCountriesUseCase(None).fold(
                {
                    data.error()
                }, ::handleGetCountries
            )
        }
    }

    fun registerUser() {
        data.loading()
        execute {
            doRegisterUseCase(
                DoRegisterUseCase.Params(
                    data.names.value,
                    data.lastName.value,
                    data.email.value,
                    data.password.value,
                    data.phone.value,
                    data.country.value,
                    data.city.value
                )
            ).fold(
                {
                    ::handleRegisterFail
                }, ::handleRegisterUserSuccess
            )
        }
    }

    private fun handleRegisterUserSuccess(userModel: UserModel) {
        data.success()
    }


    fun goToRegisterStep2() {
        if (data.password.value != data.confirmPassword.value) {
            Toast.makeText(context, "Las contraseñas no conciden", Toast.LENGTH_LONG).show()
        } else {
            data.navigateToRegisterStep2State()
        }
    }

    private fun handleGetCountries(countries: CountryModel) {
        val countriesString = ArrayList<String>()
        countries.countryList.forEach {
            countriesString.add(it.name)
        }
        data.submitCountries(countriesString)
    }

    private fun handleRegisterFail(failure: Failure) {
        data.error()
        when (failure) {
            is Failure.UserAlreadyExist -> {
                data.updateUserExist()
            }
            else -> {
                data.error()
            }
        }
    }
}