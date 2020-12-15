package com.cristhianbonilla.revistaprioridades.di.data

import android.os.Build
import com.cristhianbonilla.data.main.preference.Preferences
import com.cristhianbonilla.data.main.security.HashSecurity
import com.cristhianbonilla.data.source.secure.PreferenceSecure
import com.cristhianbonilla.data.source.secure.SecureLocalSource
import com.cristhianbonilla.revistaprioridades.platform.preferences.SharedPreferences
import com.cristhianbonilla.revistaprioridades.platform.security.AESEncryption
import com.cristhianbonilla.revistaprioridades.platform.security.HandlerHash
import com.cristhianbonilla.revistaprioridades.platform.security.PSAEncryption
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_PREFERENCES = "preferences"

val securityModule = module {

    single<Preferences> {
        SharedPreferences(
            context = get(),
            id = get(named(BASE_PREFERENCES))
        )
    }

    single<HashSecurity> { HandlerHash() }

    single {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            AESEncryption()
        else
            PSAEncryption(context = get())
    }

    single<SecureLocalSource> { PreferenceSecure(prefs = get(), hash = get(), encryption = get()) }
}