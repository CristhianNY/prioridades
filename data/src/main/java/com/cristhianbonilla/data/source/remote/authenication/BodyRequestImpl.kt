package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.entity.authenticatin.AuthEntity

class BodyRequestImpl : RequestBodyBuilder {
    override fun createLogin(username: String, password: String) = AuthEntity(
        username,
        password
    )
}