package com.cristhianbonilla.revistaprioridades.di.data.keywords

import com.cristhianbonilla.data.repository.keywords.KeyWordsRepositoryImpl
import com.cristhianbonilla.data.source.local.keywords.KeyWordsLocalImpl
import com.cristhianbonilla.data.source.local.keywords.KeyWordsLocalSource
import com.cristhianbonilla.domain.repository.keywords.KeyWordsRepository
import org.koin.dsl.module

internal val keywordsDataModule = module {
    single<KeyWordsLocalSource> {
        KeyWordsLocalImpl(assetJsonReader = get())
    }
    single<KeyWordsRepository> { KeyWordsRepositoryImpl(keywordRemoteSoruce = get()) }
}