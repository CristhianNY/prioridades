package com.cristhianbonilla.data.entity.payments.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditCardTokenRequest(
    @Json(name = "command")
    val command: String? = "CREATE_TOKEN",
    @Json(name = "creditCardToken")
    val creditCardToken: CreditCardToken?,
    @Json(name = "language")
    val language: String? = "es",
    @Json(name = "merchant")
    val merchant: Merchant?
)