package com.cristhianbonilla.data.source.local.authentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result

interface AuthLocalSource: LocalSource {
    suspend fun setUser(
        documentType: String,
        username: String
    )

    suspend fun getUser(): Result<Failure, Pair<String, String>>
}