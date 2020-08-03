package com.cristhianbonilla.domain.repository.auth

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.usecase.UseCase
import java.security.Signature
import com.cristhianbonilla.domain.functional.Result

interface AuthRepository{

    suspend fun doLogin(
        positions: List<String>,
        password: String
    ): Result<Failure, UseCase.None>

    suspend fun doLogout(): Result<Failure, UseCase.None>
}