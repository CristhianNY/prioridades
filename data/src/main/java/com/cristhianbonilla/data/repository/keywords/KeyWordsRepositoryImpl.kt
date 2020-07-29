package com.cristhianbonilla.data.repository.keywords

import com.cristhianbonilla.data.source.local.keywords.KeyWordsLocalSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.repository.keywords.KeyWordsRepository


class KeyWordsRepositoryImpl(private val keywordRemoteSoruce: KeyWordsLocalSource) :
    KeyWordsRepository {

    override suspend fun getKeyWordsList(): Result<Failure, KeyWordModel> {
        return keywordRemoteSoruce.getKeyWords()
    }
}