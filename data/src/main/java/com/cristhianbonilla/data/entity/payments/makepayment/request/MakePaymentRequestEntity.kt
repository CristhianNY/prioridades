package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakePaymentRequestEntity(
    @Json(name = "command")
    val command: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "merchant")
    val merchantEntity: MerchantEntity?,
    @Json(name = "test")
    val test: Boolean?,
    @Json(name = "transaction")
    val transactionEntity: TransactionEntity?
)