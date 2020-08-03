package com.cristhianbonilla.data.entity.authenticatin

import com.squareup.moshi.Json

data class RequestLogin(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String)