package com.cristhianbonilla.data.entity.authenticatin

import com.squareup.moshi.Json

class LogoutRequest(
    @Json(name = "accessToken") val accessToken: String
)