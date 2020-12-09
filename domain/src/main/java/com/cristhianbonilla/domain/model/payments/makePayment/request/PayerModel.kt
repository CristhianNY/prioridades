package com.cristhianbonilla.domain.model.payments.makePayment.request


data class PayerModel(
    val contactPhone: String,
    val dniNumber: String,
    val emailAddress: String,
    val fullName: String,
    val merchantPayerId: String
)
