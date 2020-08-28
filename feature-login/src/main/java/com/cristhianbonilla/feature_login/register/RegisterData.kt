package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.register.RegisterUserState.*
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class RegisterData(
    var countryList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var countryListDialCode: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var toolBarTitle: MyLiveData<String> = MyLiveData("Registrar Usuario"),
    var names: MyLiveData<String> = MyLiveData(""),
    var lastName: MyLiveData<String> = MyLiveData(""),
    var email: MyLiveData<String> = MyLiveData(""),
    var password: MyLiveData<String> = MyLiveData(""),
    var confirmPassword: MyLiveData<String> = MyLiveData(""),
    var country: MyLiveData<String> = MyLiveData(""),
    var city: MyLiveData<String> = MyLiveData(""),
    var phone: MyLiveData<String> = MyLiveData(""),
    var checkTerm: MyLiveData<Boolean> = MyLiveData(false),
    var countryCode: MyLiveData<String> = MyLiveData("+57")
) : BaseData<RegisterUserState>() {

    override fun loading() {
        super.loading()
        this updateState Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun updateUserExist() {
        this updateState UserAlreadyExist
    }

    fun updateCheck(isChecked: Boolean) {
        checkTerm update isChecked
    }

    fun updateDataFromStep1(
        namesUpdate: String,
        lasNamesUpdate: String,
        emailUpdate: String,
        passwordUpdate: String
    ) {
        names.update(namesUpdate)
        lastName update lasNamesUpdate
        email update emailUpdate
        password update passwordUpdate

    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(UserRegistrationSuccess)
    }

    fun updateCountry(countryUpdate: String) {
        country update countryUpdate
    }

    fun updateCountryDialCode(countryUpdateDialCode: String) {
        countryCode update countryUpdateDialCode
    }

    fun navigateToRegisterStep2State(
        names: String,
        lastNames: String,
        email: String,
        password: String
    ) {
        updateState(NavigateToRegisterStep2(names, lastNames, email, password))
    }

    fun submitCountries(countries: List<String>) {
        countryList update countries
        toolBarTitle update "RegistraUSuario"
    }

    fun submitCountriesDialCode(countriesDialCode: List<String>) {
        countryListDialCode update countriesDialCode
    }

}
