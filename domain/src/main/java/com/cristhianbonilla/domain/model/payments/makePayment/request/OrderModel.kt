package com.cristhianbonilla.domain.model.payments.makePayment.request

data class OrderModel(
    val accountId: String,
    val additionalValues: AdditionalValuesModel,
    val buyer: BuyerModel,
    val description: String,
    val language: String,
    val notifyUrl: String,
    val referenceCode: String,
    val signature: String
)