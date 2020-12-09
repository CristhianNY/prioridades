package com.cristhianbonilla.data.entity.payments.makepayment.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MakePaymentResponseEntity(
    @Json(name = "code")
    val code: String?,
    @Json(name = "error")
    val error: String?,
    @Json(name = "transactionResponse")
    val transactionResponse: TransactionResponse?
)