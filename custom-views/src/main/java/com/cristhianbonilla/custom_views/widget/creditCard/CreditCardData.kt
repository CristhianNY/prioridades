package com.cristhianbonilla.custom_views.widget.creditCard

data class CreditCardData(
    val creditCardNumber: String,
    val expirationDate: String,
    val nameInCard: String,
    val cvv: String,
    val countryPayment: String,
    val cardType: String
)