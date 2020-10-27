package com.cristhianbonilla.data.entity.payments.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditCardTokenEntity(
    @Json(name = "code")
    val code: String?,
    @Json(name = "creditCardToken")
    val creditCardTokenizedEntity: CreditCardTokenizedEntity?
)