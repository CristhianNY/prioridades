package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(
    @Json(name = "accountId")
    val accountId: String?,
    @Json(name = "additionalValues")
    val additionalValuesEntity: AdditionalValuesEntity,
    @Json(name = "buyer")
    val buyer: Buyer,
    @Json(name = "description")
    val description: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "referenceCode")
    val referenceCode: String?,
    @Json(name = "signature")
    val signature: String?
)