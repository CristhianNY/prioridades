package com.cristhianbonilla.domain.model.payments.makePayment.request

data class CreditCardModel(
    val processWithoutCvv2: String?,
    val securityCode: String?
)