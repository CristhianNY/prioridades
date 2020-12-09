package com.cristhianbonilla.domain.model.payments.makePayment.request


data class MakePaymentRequestModel(
    val command: String,
    val language: String,
    val merchant: MerchantModel,
    val test: Boolean,
    val transaction: TransactionModel
)