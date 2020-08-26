package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.entity.authenticatin.toModel
import com.cristhianbonilla.data.entity.profile.toModel
import com.cristhianbonilla.data.source.remote.authenication.login.api.LoginApi
import com.cristhianbonilla.data.source.remote.authenication.login.api.LoginApi.Companion.SIGNED_REQUEST_HEADER
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.authentication.UserAuthModel
import com.cristhianbonilla.domain.model.profile.UserModel

class AuthService(
    private val api: LoginApi,
    private val authRequestBodyBuilder: RequestBodyBuilder
) : AuthRemoteSource {

    override suspend fun postLogin(
        username: String,
        password: String,
        signedRequest: String?
    ) = request(
        {
            val headers = if (signedRequest == null) {
                emptyMap()
            } else {
                mapOf(SIGNED_REQUEST_HEADER to signedRequest)
            }
            var body = authRequestBodyBuilder.createLogin(username, password)
            api.postLogin(
                headers,
                body
            )
        },
        { loginEntity, _ -> loginEntity.toModel() }
    )

    override suspend fun postRegister(
        name: String,
        lastNames: String,
        email: String,
        password: String,
        phone: String,
        country: String,
        city: String
    ): Result<Failure, UserModel> {
        return request({
            api.registerUser(email,name,lastNames,country,city,phone,password)
        }, { entity, _ -> entity.toModel() })
    }
}