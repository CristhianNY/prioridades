package com.cristhianbonilla.domain.model.payments.makePayment.response

data class MakePaymentResponseModel(
    val code: String?,
    val error: String?,
    val transactionResponseModel: TransactionResponseModel?
)