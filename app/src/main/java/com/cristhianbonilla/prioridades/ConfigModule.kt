package com.cristhianbonilla.prioridades

import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.prioridades.di.data.BASE_PATH_URL
import com.cristhianbonilla.prioridades.di.data.BASE_PREFERENCES
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun configModule(
    mode: ModeTypeModel,
    baseUrl: String
) = module {

    single { mode }
    single(named(BASE_PREFERENCES)) { "mbPreferences" }
    single(named(BASE_PATH_URL)) { baseUrl }
}