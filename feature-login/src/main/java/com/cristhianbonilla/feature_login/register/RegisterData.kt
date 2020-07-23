package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.domain.model.countries.CountryItemModel
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData
import com.cristhianbonilla.feature_login.register.RegisterUserState.Loading
import com.cristhianbonilla.feature_login.register.RegisterUserState.Error
import com.cristhianbonilla.feature_login.register.RegisterUserState.Success

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
