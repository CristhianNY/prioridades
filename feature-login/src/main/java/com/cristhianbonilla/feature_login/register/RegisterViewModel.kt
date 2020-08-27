package com.cristhianbonilla.feature_login.register

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase.None
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.domain.usecase.register.DoRegisterUseCase
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

    fun fillDataFromFragmentRegisterOne(
        namesUpdate: String,
        lasNamesUpdate: String,
        emailUpdate: String,
        passwordUpdate: String
    ) {
        data.updateDataFromStep1(namesUpdate, lasNamesUpdate, emailUpdate, passwordUpdate)
    }

    fun registerUser() {
        if (data.country.value != "País") {
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
                ).fold(::handleRegisterFail, ::handleRegisterUserSuccess)
            }
        } else {
            Toast.makeText(context, "Por favor selecciona tu pais", Toast.LENGTH_LONG).show()
        }

    }

    private fun handleRegisterUserSuccess(userModel: UserModel) {
        data.success()
    }


    fun goToRegisterStep2() {
        if (data.password.value != data.confirmPassword.value) {
            Toast.makeText(context, "Las contraseñas no conciden", Toast.LENGTH_LONG).show()
        } else {
            data.navigateToRegisterStep2State(
                data.names.value,
                data.lastName.value,
                data.email.value,
                data.password.value
            )
        }
    }

    val clicksListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            data.updateCountry("País")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val country
                    = parent?.getItemAtPosition(position) as String
            data.updateCountry(country)
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

    override fun onCleared() {
        super.onCleared()
    }
}