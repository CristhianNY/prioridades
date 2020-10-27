package com.cristhianbonilla.prioridades

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.prioridades.BuildConfig.*
import com.cristhianbonilla.prioridades.config.AppConfigurationModel
import com.cristhianbonilla.prioridades.di.getModules

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