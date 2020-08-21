package com.cristhianbonilla.data.entity.authenticatin

import com.cristhianbonilla.domain.model.authentication.UserAuthModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginEntity(
    @Json(name = "token")
    val token: String
)

fun LoginEntity.toModel() = UserAuthModel(token)