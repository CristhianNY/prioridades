package com.cristhianbonilla.data.source.remote.authenication

import com.cristhianbonilla.data.entity.authenticatin.AuthEntity

interface RequestBodyBuilder {
    fun createLogin(
        username: String,
        password: String
    ):AuthEntity
}