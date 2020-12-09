package com.cristhianbonilla.domain.model.payments.request

data class CreditCardTokenRequestModel(
    val command: String? = "CREATE_TOKEN",
    val language: String? = "es",
    val apiKey: String?="F4vaKr28jSOCdC3Tx2IMCD8XW5",
    val apiLogin: String? = "AZX1vW8cw5Aq36B",
    val expirationDate: String?,
    val name: String?,
    val number: String?,
    val payerId: String?,
    val paymentMethod: String?
)