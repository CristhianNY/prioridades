package com.cristhianbonilla.prioridades.di.data

import com.cristhianbonilla.data.main.NetworkStatus
import com.cristhianbonilla.data.session.SessionHandler
import com.cristhianbonilla.data.source.interceptor.PrivateInterceptor
import com.cristhianbonilla.data.source.interceptor.PublicInterceptor
import com.cristhianbonilla.domain.session.Session
import com.cristhianbonilla.prioridades.platform.network.NetworkHandler
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Authenticator
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val HTTPS_PREFIX = "https://"
const val BASE_PATH_URL = "baseUrl"
internal const val INTERCEPTOR_PRIVATE = "privateInterceptor"
const val REMOTE_CLIENT_PUBLIC = "publicRetrofit"
internal const val CERTIFICATE_PINNER = "certificatePinner"
const val REMOTE_CLIENT_PRIVATE = "privateRetrofit"
const val CERTIFICATE_FINGERPRINT = "certificateFingerprint"

private const val HTTP_CLIENT_TIME_OUT_SECONDS = 60L

val networkModule = module {
    single<NetworkStatus> { NetworkHandler(context = get()) }

    single<Session> {
        SessionHandler(
            authRemoteSource = get(),
            secureLocalSource = get()
        )
    }

    single<Authenticator> { PublicInterceptor(session = get()) }


    single<Interceptor>(named(INTERCEPTOR_PRIVATE)) {
        PrivateInterceptor(
            session = get(),
            networkStatus = get(),
            localHandler = get()
        )
    }

    single(named(CERTIFICATE_PINNER)) {
        CertificatePinner.Builder()
            .add(
                get<String>(named(BASE_PATH_URL)).removePrefix(
                    HTTPS_PREFIX
                ),
                get(named(CERTIFICATE_FINGERPRINT))
            )
            .build()
    }

    single<Retrofit>(named(REMOTE_CLIENT_PUBLIC)) {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val httpClient = OkHttpClient.Builder()
            .apply {
                readTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                writeTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                connectTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                interceptors().add(logging)
            }
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_PATH_URL)))
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    single<Retrofit>(named(REMOTE_CLIENT_PRIVATE)) {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val httpClient = OkHttpClient.Builder()
            .apply {
                readTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                writeTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                connectTimeout(HTTP_CLIENT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                authenticator(authenticator = get())
                addInterceptor(interceptor = get(named(INTERCEPTOR_PRIVATE)))
                interceptors().add(logging)
            }
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_PATH_URL)))
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

}