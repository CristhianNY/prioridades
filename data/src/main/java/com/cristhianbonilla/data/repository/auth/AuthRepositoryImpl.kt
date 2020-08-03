package com.cristhianbonilla.data.repository.auth

import com.cristhianbonilla.data.source.local.authentication.AuthLocalSource
import com.cristhianbonilla.data.source.platform.DevicePlatformSource
import com.cristhianbonilla.data.source.remote.authenication.AuthRemoteSource
import com.cristhianbonilla.data.source.secure.SecureKeyPreferences
import com.cristhianbonilla.data.source.secure.SecureLocalSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.functional.Result.Error
import com.cristhianbonilla.domain.functional.Result.Success
import com.cristhianbonilla.domain.functional.getOrNull
import com.cristhianbonilla.domain.repository.auth.AuthRepository
import com.cristhianbonilla.domain.session.Session
import com.cristhianbonilla.domain.usecase.UseCase

class AuthRepositoryImpl(
    private val authRemoteSource: AuthRemoteSource,
    private val authLocalSource: AuthLocalSource,
    private val secureLocalSource: SecureLocalSource,
    private val devicePlatformSource: DevicePlatformSource,
    private val session: Session
) : AuthRepository {
    override suspend fun doLogin(
        positions: List<String>,
        password: String
    ): Result<Failure, UseCase.None> =
        try {
            val deviceInfo = devicePlatformSource.getDeviceInfo()
            authLocalSource.getUser()
                .getOrNull()
                ?.let { user ->
                    authRemoteSource.postLogin(
                        user.first,
                        user.second
                    )
                        .getOrNull()
                        ?.let { loginUser ->
                            secureLocalSource.setValue(
                                SecureKeyPreferences.USER_DOCUMENT_TYPE,
                                user.first
                            )
                            secureLocalSource.setValue(
                                SecureKeyPreferences.USER_DOCUMENT,
                                user.second
                            )
                            session.login(loginUser.accessToken, loginUser.refreshToken)
                            return Result.Success(UseCase.None)
                        }
                }
            Error(Failure.RemoteError())
        } catch (exception: Exception) {
            Error(Failure.RemoteError())
        }

    override suspend fun doLogout() =
        if (session.logout()) Success(UseCase.None) else Error(Failure.LocalError)
}