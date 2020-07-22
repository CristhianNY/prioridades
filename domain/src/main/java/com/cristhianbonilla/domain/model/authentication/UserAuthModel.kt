package com.cristhianbonilla.domain.model.authentication

data class UserAuthModel(
    val accessToken: String,
    val refreshToken: String,
    val appPassword: String?
)