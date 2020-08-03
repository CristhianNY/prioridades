package com.cristhianbonilla.data.entity.authenticatin

import com.cristhianbonilla.domain.model.authentication.UserAuthModel
import com.squareup.moshi.Json

data class LoginEntity(
    @Json(name = "accessToken") val accessToken: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "appPassword") val appPassword: String?
)

fun LoginEntity.toModel() = UserAuthModel(accessToken, refreshToken, appPassword)