package com.cristhianbonilla.domain.model.payments.makePayment.request


data class TransactionModel(
    val creditCardModel: CreditCardModel,
    val cookie: String,
    val creditCardTokenId: String,
    val deviceSessionId: String,
    val ipAddress: String,
    val order: OrderModel,
    val payer: PayerModel,
    val paymentCountry: String,
    val paymentMethod: String,
    val type: String,
    val userAgent: String
)