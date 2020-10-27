package com.cristhianbonilla.domain.model.payments.response

data class CreditCardTokenizedModel(

    val creationDate: String?,
    val creditCardTokenId: String?,
    val errorDescription: String?,
    val expirationDate: String?,
    val identificationNumber: String?,
    val maskedNumber: String?,
    val name: String?,
    val number: String?,
    val payerId: String?,
    val paymentMethod: String?
)