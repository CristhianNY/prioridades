package com.cristhianbonilla.data.repository.auth

import com.cristhianbonilla.data.source.local.authentication.AuthLocalSource
import com.cristhianbonilla.data.source.platform.DevicePlatformSource
import com.cristhianbonilla.data.source.remote.authenication.AuthRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.functional.CustomResult.Error
import com.cristhianbonilla.domain.functional.CustomResult.Success
import com.cristhianbonilla.domain.functional.getOrNull
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.repository.auth.AuthRepository
import com.cristhianbonilla.domain.session.Session
import com.cristhianbonilla.domain.usecase.UseCase

class AuthRepositoryImpl(
    private val authRemoteSource: AuthRemoteSource,
    private val authLocalSource: AuthLocalSource,
    private val devicePlatformSource: DevicePlatformSource,
    private val session: Session
) : AuthRepository {
    override suspend fun doLogin(
        userName: String,
        password: String
    ): CustomResult<Failure, UseCase.None> =
        try {
            authRemoteSource.postLogin(
                userName,
                password
            )
                .getOrNull()
                ?.let { loginUser ->
                    session.login(loginUser.token)
                    return Success(UseCase.None)
                }
            Error(Failure.RemoteError())
        } catch (exception: Exception) {
            Error(Failure.RemoteError())
        }


    override suspend fun doLogout() =
        if (session.logout()) Success(UseCase.None) else Error(Failure.LocalError)

    override fun isLogged() = session.isTokenSaved()
    override suspend fun doRegister(
        email: String,
        names: String,
        lastNames: String,
        country: String,
        city: String,
        phone: String,
        password: String
    ): CustomResult<Failure, UserModel> {
        return authRemoteSource.postRegister(
            names,
            lastNames,
            email,
            password,
            phone,
            country,
            city
        )
    }
}