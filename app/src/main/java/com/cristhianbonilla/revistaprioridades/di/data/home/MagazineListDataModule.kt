package com.cristhianbonilla.revistaprioridades.di.data.home

import com.cristhianbonilla.data.repository.home.MagazineRepositoryImpl
import com.cristhianbonilla.data.source.remote.home.MagazineRemoteSource
import com.cristhianbonilla.data.source.remote.home.MagazineRemoteSourceImpl
import com.cristhianbonilla.data.source.remote.home.api.MagazineListApi
import com.cristhianbonilla.domain.repository.home.MagazineRepository
import com.cristhianbonilla.revistaprioridades.di.data.REMOTE_CLIENT_PRIVATE
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val MagazineListDataModule  = module {
    single<MagazineListApi> {
        get<Retrofit>(named(REMOTE_CLIENT_PRIVATE)).create(
            MagazineListApi::class.java
        )
    }

    single<MagazineRemoteSource> {
        MagazineRemoteSourceImpl(
            magazineAPi = get()
        )
    }

    single<MagazineRepository> {
        MagazineRepositoryImpl(
            magazineRemoteSource = get()
        )
    }
}