package com.cristhianbonilla.prioridades.di.data.authenticatin

import com.cristhianbonilla.data.main.security.SecurityProviderImpl
import com.cristhianbonilla.data.repository.auth.AuthRepositoryImpl
import com.cristhianbonilla.data.source.local.authentication.AuthCache
import com.cristhianbonilla.data.source.local.authentication.AuthLocalSource
import com.cristhianbonilla.data.source.platform.DevicePlatformSource
import com.cristhianbonilla.data.source.remote.authenication.AuthRemoteSource
import com.cristhianbonilla.data.source.remote.authenication.AuthService
import com.cristhianbonilla.data.source.remote.authenication.BodyRequestImpl
import com.cristhianbonilla.data.source.remote.authenication.RequestBodyBuilder
import com.cristhianbonilla.data.source.remote.authenication.login.api.LoginApi
import com.cristhianbonilla.domain.repository.auth.AuthRepository
import com.cristhianbonilla.domain.security.ProviderSecurity
import com.cristhianbonilla.prioridades.di.data.REMOTE_CLIENT_PUBLIC
import com.cristhianbonilla.prioridades.platform.devices.HandlerDevice
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val authenticationDataModule = module {

    single<LoginApi> { get<Retrofit>(named(REMOTE_CLIENT_PUBLIC)).create(LoginApi::class.java) }

    single<RequestBodyBuilder> { BodyRequestImpl() }

    single<AuthRemoteSource> { AuthService(api = get(), authRequestBodyBuilder = get()) }

    single<AuthLocalSource> { AuthCache() }

    single<DevicePlatformSource> { HandlerDevice(context = get()) }

    single<ProviderSecurity> { SecurityProviderImpl(
        secureLocalSource = get()
    )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            authRemoteSource = get(),
            authLocalSource = get(),
            devicePlatformSource = get(),
            session = get()
        )
    }
}