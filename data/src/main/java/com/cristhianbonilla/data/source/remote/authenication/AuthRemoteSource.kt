package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.authentication.UserAuthModel
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.UseCase.None


interface AuthRemoteSource : RemoteSource {
    suspend fun postLogin(
        username: String,
        password: String,
        signedRequest: String? = null
    ): Result<Failure, UserAuthModel>

    suspend fun postRegister(
        name: String,
        lastNames: String,
        email: String,
        password: String,
        phone: String,
        country: String,
        city: String
    ): Result<Failure, UserModel>
}