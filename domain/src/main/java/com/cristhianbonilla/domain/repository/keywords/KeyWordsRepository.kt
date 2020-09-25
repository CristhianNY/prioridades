package com.cristhianbonilla.domain.repository.keywords

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.keywords.KeyWordModel

interface KeyWordsRepository {
    suspend fun getKeyWordsList(): CustomResult<Failure, KeyWordModel>
}