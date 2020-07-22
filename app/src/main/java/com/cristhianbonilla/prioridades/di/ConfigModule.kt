package com.cristhianbonilla.prioridades.di

import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.prioridades.di.data.BASE_PATH_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun configModule(
    mode: ModeTypeModel,
    baseUrl: String
) = module {

    single { mode }

    single(named(BASE_PATH_URL)) { baseUrl }
}