package com.cristhianbonilla.prioridades.di.data

import com.cristhianbonilla.data.main.NetworkStatus
import com.cristhianbonilla.data.session.SessionHandler
import com.cristhianbonilla.domain.session.Session
import com.cristhianbonilla.prioridades.platform.network.NetworkHandler
import org.koin.dsl.module

const val HTTPS_PREFIX = "https://"
const val BASE_PATH_URL = "baseUrl"
const val CERTIFICATE_FINGERPRINT = "certificateFingerprint"
internal const val INTERCEPTOR_PRIVATE = "privateInterceptor"
internal const val CERTIFICATE_PINNER = "certificatePinner"
const val REMOTE_CLIENT_PUBLIC = "publicRetrofit"
const val REMOTE_CLIENT_PRIVATE = "privateRetrofit"

private const val HTTP_CLIENT_TIME_OUT_SECONDS = 60L

val networkModule = module {
    single<NetworkStatus> { NetworkHandler(context = get()) }

    single<Session> {
        SessionHandler(
            authRemoteSource = get(),
            secureLocalSource = get()
        )
    }

}