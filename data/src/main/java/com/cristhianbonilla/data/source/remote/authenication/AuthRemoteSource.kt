package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.authentication.UserAuthModel
import com.cristhianbonilla.domain.usecase.UseCase.None
import com.cristhianbonilla.domain.functional.Result

interface AuthRemoteSource : RemoteSource {
    suspend fun postLogin(
        username: String,
        password: String
    ): Result<Failure, UserAuthModel>

    suspend fun postRefresh(
        username: String,
        accessToken: String,
        refreshToken: String
    ): Result<Failure, UserAuthModel>

    suspend fun postLogout(
        accessToken: String
    ): Result<Failure, None>

    suspend fun postRecoverSignatureKey(
        signatureKey: String
    ): Result<Failure, None>
}