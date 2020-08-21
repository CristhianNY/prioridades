package com.cristhianbonilla.data.entity.authenticatin


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthEntity(
    @Json(name = "email")
    val email: String?,
    @Json(name = "pass")
    val pass: String?
)