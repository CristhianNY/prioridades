package com.cristhianbonilla.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristhianbonilla.domain.usecase.authtentication.GetLoginStatus
import com.cristhianbonilla.foundations.livedata.MyLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val getLoginStatus: GetLoginStatus) : ViewModel() {


    var isLogged : MyLiveData<Boolean> = MyLiveData(false)

    fun getLoginStatus() {
        viewModelScope.launch {
          isLogged update getLoginStatus.isLogin()

        }
    }


}