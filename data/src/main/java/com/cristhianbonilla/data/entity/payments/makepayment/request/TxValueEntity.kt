package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TxValueEntity(
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "value")
    val value: Int?
)