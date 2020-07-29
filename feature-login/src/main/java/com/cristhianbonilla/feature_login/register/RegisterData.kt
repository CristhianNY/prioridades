package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.register.RegisterUserState.*
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class RegisterData(
    var countryList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var toolBarTitle: MyLiveData<String> = MyLiveData("Registrar Usuario")
) : BaseData<RegisterUserState>(){

    override fun loading() {
        super.loading()
        this updateState Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(Success)
    }


    fun submitCountries(countries:List<String>){
        countryList update countries
        toolBarTitle update "RegistraUSuario"
    }

}
