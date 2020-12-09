package com.cristhianbonilla.data.entity.payments.makepayment.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionEntity(
    @Json(name = "creditCard")
    val creditCardEntity: CreditCardEntity?,
    @Json(name = "creditCardTokenId")
    val creditCardTokenId: String?,
    @Json(name = "ipAddress")
    val ipAddress: String?,
    @Json(name = "order")
    val order: Order?,
    @Json(name = "payer")
    val payer: Payer?,
    @Json(name = "paymentCountry")
    val paymentCountry: String?,
    @Json(name = "paymentMethod")
    val paymentMethod: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "userAgent")
    val userAgent: String?
)