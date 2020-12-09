package com.cristhianbonilla.domain.model.payments.makePayment.response


data class TransactionResponseModel(
    val authorizationCode: String,
    val errorCode: String,
    val extraParameters: String,
    val operationDate: String,
    val paymentNetworkResponseCode: String?,
    val paymentNetworkResponseErrorMessage: String,
    val pendingReason: String,
    val responseCode: String?,
    val responseMessage: String,
    val state: String?,
    val transactionDate: String,
    val transactionId: String?,
    val transactionTime: String,
    val trazabilityCode: String?
)