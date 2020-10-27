package com.cristhianbonilla.data.entity.payments.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Merchant(
    @Json(name = "apiKey")
    val apiKey: String?,
    @Json(name = "apiLogin")
    val apiLogin: String?
)