package com.cristhianbonilla.feature_login.login

import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

data class LoginData(
    var username: MyLiveData<String> = MyLiveData(""),
    var showPass: MyLiveData<Boolean> = MyLiveData(false),
    var password: MyLiveData<String> = MyLiveData("")
) : BaseData<LoginState>() {

    override fun loading() {
        super.loading()
        this updateState LoginState.LoadingLogin
    }

    override fun error() {
        super.error()
        this updateState LoginState.ErrorLogin
    }

    fun success() {
        this updateState LoginState.SuccessLogin
    }

    fun userAlreadyLogged(){
        this updateState LoginState.UserAlreadyLogged
    }

    fun registerState(){
        this updateState LoginState.RegisterUser
    }

}