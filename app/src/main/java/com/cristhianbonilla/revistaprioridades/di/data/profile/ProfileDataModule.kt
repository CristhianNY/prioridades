package com.cristhianbonilla.revistaprioridades.di.data.profile

import com.cristhianbonilla.data.repository.profile.UserProfileRepositoryImpl
import com.cristhianbonilla.data.source.remote.profile.UserProfileRemoteSource
import com.cristhianbonilla.data.source.remote.profile.UserProfileRemoteSourceImpl
import com.cristhianbonilla.data.source.remote.profile.api.UserInformationApi
import com.cristhianbonilla.domain.repository.profile.UserProfileRepository
import com.cristhianbonilla.revistaprioridades.di.data.REMOTE_CLIENT_PRIVATE
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val profileDataModule = module {
    single<UserInformationApi> {
        get<Retrofit>(named(REMOTE_CLIENT_PRIVATE)).create(
            UserInformationApi::class.java
        )
    }

    single<UserProfileRemoteSource> {
        UserProfileRemoteSourceImpl(
            userInformationApi = get()
        )
    }

    single<UserProfileRepository> {
        UserProfileRepositoryImpl(
            userProfileSource = get()
        )
    }
}