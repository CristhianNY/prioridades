package com.cristhianbonilla.domain.model.payments.makePayment.request

data class BuyerModel(
    val contactPhone: String?,
    val dniNumber: String?,
    val emailAddress: String?,
    val fullName: String?,
    val merchantBuyerId: String?
)