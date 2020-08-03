package com.cristhianbonilla.data.entity.authenticatin

import com.squareup.moshi.Json

class RequestRefresh (
    @Json(name = "username") val username: String,
    @Json(name = "refreshToken") val refreshToken: String)