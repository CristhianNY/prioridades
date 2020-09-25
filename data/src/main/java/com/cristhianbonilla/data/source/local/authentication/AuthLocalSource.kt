package com.cristhianbonilla.data.source.local.authentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult

interface AuthLocalSource: LocalSource {
    suspend fun setUser(
        documentType: String,
        username: String
    )

    suspend fun getUser(): CustomResult<Failure, Pair<String, String>>
}