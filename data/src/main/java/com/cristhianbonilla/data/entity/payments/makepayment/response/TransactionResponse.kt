package com.cristhianbonilla.data.entity.payments.makepayment.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionResponse(
    @Json(name = "authorizationCode")
    val authorizationCode: String?,
    @Json(name = "errorCode")
    val errorCode: String?,
    @Json(name = "extraParameters")
    val extraParameters: String?,
    @Json(name = "operationDate")
    val operationDate: String?,
    @Json(name = "orderId")
    val orderId: Int?,
    @Json(name = "paymentNetworkResponseCode")
    val paymentNetworkResponseCode: String?,
    @Json(name = "paymentNetworkResponseErrorMessage")
    val paymentNetworkResponseErrorMessage: String?,
    @Json(name = "pendingReason")
    val pendingReason: String?,
    @Json(name = "responseCode")
    val responseCode: String?,
    @Json(name = "responseMessage")
    val responseMessage: String?,
    @Json(name = "state")
    val state: String?,
    @Json(name = "transactionDate")
    val transactionDate: String?,
    @Json(name = "transactionId")
    val transactionId: String?,
    @Json(name = "transactionTime")
    val transactionTime: String?,
    @Json(name = "trazabilityCode")
    val trazabilityCode: String?
)