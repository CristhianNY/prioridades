package com.cristhianbonilla.prioridades.di

import android.util.Log
import com.cristhianbonilla.data.source.local.LocalHandler
import com.cristhianbonilla.domain.reporter.*
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.prioridades.App
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appModule = module {


    single<Reporter> {
        object : Reporter {
            override fun initialize(initializer: ReporterInitializer) {}

            override fun log(
                key: String,
                value: String,
                logSeverityLevel: ReporterLogSeverityLevel
            ) {
                Log.d(key, value)
            }

            override fun exception(
                exception: Exception,
                logSeverityLevel: ReporterLogSeverityLevel
            ) {
                Log.e("Error", exception.toString())
            }

            override fun recordBreadcrumb(breadCrumb: ReporterBreadCrumb) {}

            override fun setUserData(userData: ReporterUserData) {}
        }
    }

    single<LocalHandler> {
        object : LocalHandler {
            override fun invalidateCaches() {}
        }
    }
}