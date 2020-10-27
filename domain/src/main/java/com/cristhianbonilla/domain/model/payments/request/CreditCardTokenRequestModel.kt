package com.cristhianbonilla.domain.model.payments.request

data class CreditCardTokenRequestModel(
    val command: String? = "CREATE_TOKEN",
    val language: String? = "es",
    val apiKey: String?,
    val apiLogin: String?,
    val expirationDate: String?,
    val identificationNumber: String?,
    val name: String?,
    val number: String?,
    val payerId: String?,
    val paymentMethod: String?
)