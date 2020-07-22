package com.cristhianbonilla.foundations.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cristhianbonilla.foundations.livedata.MyLiveData

abstract class BaseData<S : BaseState>(
    private val state: MutableLiveData<S> = MutableLiveData(),
    var showLoading: MyLiveData<Boolean> = MyLiveData(false),
    var showError: MyLiveData<Boolean> = MyLiveData(false),
    var trustedButtonText: MyLiveData<String>  = MyLiveData("Trust Device")
) {

    infix fun updateState(newState: S) {
        state.postValue(newState)
    }

    @CallSuper
    open fun loading() {
        showLoading update true
        showError update false
    }

    @CallSuper
    open fun error() {
        showLoading update false
        showError update true
    }

    open fun trusted(){
        trustedButtonText update "Untrust Device"
    }

    open fun untrusted(){
        trustedButtonText update "Trust Device"
    }

    fun observeState(lifecycleOwner: LifecycleOwner, observer: Observer<S>) {
        state.observe(lifecycleOwner, observer)
    }

    open fun clearState() {
        state.postValue(null)
    }

}