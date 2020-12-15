package com.cristhianbonilla.revistaprioridades

import com.cristhianbonilla.domain.config.ModeTypeModel
import com.cristhianbonilla.revistaprioridades.di.data.BASE_PATH_URL
import com.cristhianbonilla.revistaprioridades.di.data.BASE_PREFERENCES
import com.cristhianbonilla.revistaprioridades.di.data.PAYU_API_KEY
import com.cristhianbonilla.revistaprioridades.di.data.PAYU_PATH_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun configModule(
    mode: ModeTypeModel,
    baseUrl: String,
    payuUrl : String,
    payuApiKey:String
) = module {

    single { mode }
    single(named(BASE_PREFERENCES)) { "mbPreferences" }
    single(named(BASE_PATH_URL)) { baseUrl }
    single(named(PAYU_PATH_URL)) { payuUrl }
    single(named(PAYU_API_KEY)) { payuApiKey }
}