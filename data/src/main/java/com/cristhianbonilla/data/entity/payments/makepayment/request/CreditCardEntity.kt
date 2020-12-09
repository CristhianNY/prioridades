package com.cristhianbonilla.data.entity.payments.makepayment.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditCardEntity(
    @Json(name = "processWithoutCvv2")
    val processWithoutCvv2: String?,
    @Json(name = "securityCode")
    val securityCode: String?
)