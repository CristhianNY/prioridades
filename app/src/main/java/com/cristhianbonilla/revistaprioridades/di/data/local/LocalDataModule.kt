package com.cristhianbonilla.revistaprioridades.di.data.local

import com.cristhianbonilla.data.source.local.AssetJsonReader
import org.koin.dsl.module

internal val localDataModule = module {

    single<AssetJsonReader> {
        AssetJsonReader(context = get())
    }

}