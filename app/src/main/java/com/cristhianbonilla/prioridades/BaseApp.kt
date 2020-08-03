package com.cristhianbonilla.prioridades

import android.app.Application
import com.cristhianbonilla.foundations.extensions.initDI
import com.cristhianbonilla.prioridades.config.AppConfigurationModel
import com.cristhianbonilla.prioridades.di.getModules
import org.koin.core.module.Module

fun Application.initCore(data: AppConfigurationModel) {
    val modules: MutableList<Module> = getModules()
    with(data) {
        modules.add(configModule(data.mode, baseUrl))
        if (moduleList.isNotEmpty()) {
            modules.addAll(moduleList)
        }
    }
    initDI(modules)

}