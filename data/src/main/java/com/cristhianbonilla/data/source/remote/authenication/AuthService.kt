package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.entity.authenticatin.toModel
import com.cristhianbonilla.data.source.remote.authenication.api.AuthenticationApi
import com.cristhianbonilla.data.source.requestBuilder.AuthenticationRequestBodyBuilder
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.functional.Result


class AuthService(
    private val api: AuthenticationApi,
    private val authRequestBodyBuilder: AuthenticationRequestBodyBuilder
) : AuthRemoteSource {

    override suspend fun postLogin(
        username: String,
        password: String
    ) = request(
        {
            api.postLogin(
                authRequestBodyBuilder.createLogin(
                    username, password
                )
            )
        },
        { loginEntity, _ -> loginEntity.toModel() }
    )


    override suspend fun postRefresh(username: String, accessToken: String, refreshToken: String) =
        request(
            {
                api.postLoginRefresh(
                    accessToken,
                    authRequestBodyBuilder.createRefresh(username, refreshToken)
                )
            },
            { loginRefreshEntity, _ -> loginRefreshEntity.toModel() }
        )

    override suspend fun postLogout(accessToken: String): Result<Failure, UseCase.None> = request(
        {
            api.postLogout(
                accessToken,
                authRequestBodyBuilder.createLogout(accessToken)
            )
        },
        { _, _ -> UseCase.None }
    )

}