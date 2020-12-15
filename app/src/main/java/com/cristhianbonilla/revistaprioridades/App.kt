package com.cristhianbonilla.revistaprioridades

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.revistaprioridades.BuildConfig.*
import com.cristhianbonilla.revistaprioridades.config.AppConfigurationModel
import com.cristhianbonilla.revistaprioridades.di.getModules

class App : Application() , LifecycleObserver {

    override fun onCreate() {
        super.onCreate()

        initCore(
            AppConfigurationModel(
                ModeTypeModel.DEBUG,
                BASE_URL,
                VERSION_CODE,
                VERSION_NAME,
                getModules(),
                PAYU_URL,
                PAYU_API_KEY
            )
        )
    }

    companion object {
        val CONTRACT_ADDRESS: String = ""
        val CONTRAC_ABI: String = ""
    }
}