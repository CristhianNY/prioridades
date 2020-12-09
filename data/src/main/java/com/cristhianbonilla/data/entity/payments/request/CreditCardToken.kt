package com.cristhianbonilla.data.entity.payments.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditCardToken(
    @Json(name = "expirationDate")
    val expirationDate: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "number")
    val number: String?,
    @Json(name = "payerId")
    val payerId: String?,
    @Json(name = "paymentMethod")
    val paymentMethod: String?
)