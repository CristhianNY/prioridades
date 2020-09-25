package com.cristhianbonilla.data.source.local.keywords

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.keywords.KeyWordModel

interface KeyWordsLocalSource : RemoteSource {
    suspend fun getKeyWords(): CustomResult<Failure, KeyWordModel>
}