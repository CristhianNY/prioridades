package com.cristhianbonilla.revistaprioridades

import android.app.Application
import com.cristhianbonilla.foundations.extensions.initDI
import com.cristhianbonilla.revistaprioridades.config.AppConfigurationModel
import com.cristhianbonilla.revistaprioridades.di.getModules
import org.koin.core.module.Module

fun Application.initCore(data: AppConfigurationModel) {
    val modules: MutableList<Module> = getModules()
    with(data) {
        modules.add(configModule(data.mode, baseUrl, payuUrl , apiKey))
        if (moduleList.isNotEmpty()) {
            modules.addAll(moduleList)
        }
    }
    initDI(modules)

}