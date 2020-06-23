package com.cristhianbonilla.prioridades

import android.app.Application

class App : Application() {

    override  fun onCreate(){
        super.onCreate()
    }

    companion object {
        val CONTRACT_ADDRESS: String = ""
        val CONTRAC_ABI: String = ""
    }
}