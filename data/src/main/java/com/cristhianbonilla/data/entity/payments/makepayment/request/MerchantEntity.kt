package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantEntity(
    @Json(name = "apiKey")
    val apiKey: String?,
    @Json(name = "apiLogin")
    val apiLogin: String?
)