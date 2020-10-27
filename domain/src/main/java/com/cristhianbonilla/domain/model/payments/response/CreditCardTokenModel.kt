package com.cristhianbonilla.domain.model.payments.response

data class CreditCardTokenModel(
    val code: String,
    val creditCardToken: CreditCardTokenizedModel
)