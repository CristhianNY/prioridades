package com.cristhianbonilla.foundations.livedata

import androidx.lifecycle.MutableLiveData

open class MyLiveData<T : Any>(private val data: T) : MutableLiveData<T>() {

    init {
        postValue(data)
    }

    final override fun getValue(): T {
        return super.getValue() ?: data
    }

    infix fun update(value: T) {
        postValue(value)
    }
}