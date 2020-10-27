package com.cristhianbonilla.prioridades.config

import com.cristhianbonilla.domain.config.ModeTypeModel
import org.koin.core.module.Module

data class AppConfigurationModel(
    val mode: ModeTypeModel,
    val baseUrl: String,
    val versionCode: Int,
    val versionName: String,
    val moduleList: List<Module>,
    val payuUrl: String,
    val apiKey:String
)