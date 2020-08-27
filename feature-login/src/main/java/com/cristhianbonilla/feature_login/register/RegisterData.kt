package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.register.RegisterUserState.*
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class RegisterData(
    var countryList: MyLiveData<List<String>> = MyLiveData(mutableListOf()),
    var toolBarTitle: MyLiveData<String> = MyLiveData("Registrar Usuario"),
    var names:MyLiveData<String> = MyLiveData(""),
    var lastName:MyLiveData<String> = MyLiveData(""),
    var email:MyLiveData<String> = MyLiveData(""),
    var password:MyLiveData<String> = MyLiveData(""),
    var confirmPassword:MyLiveData<String> = MyLiveData(""),
    var country:MyLiveData<String> = MyLiveData(""),
    var city:MyLiveData<String> = MyLiveData(""),
    var phone:MyLiveData<String> = MyLiveData("")
) : BaseData<RegisterUserState>(){

    override fun loading() {
        super.loading()
        this updateState Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun updateUserExist(){
        this updateState  UserAlreadyExist
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(Success)
    }

    fun navigateToRegisterStep2State(){
        updateState(NavigateToRegisterStep2)
    }

    fun submitCountries(countries:List<String>){
        countryList update countries
        toolBarTitle update "RegistraUSuario"
    }

}
