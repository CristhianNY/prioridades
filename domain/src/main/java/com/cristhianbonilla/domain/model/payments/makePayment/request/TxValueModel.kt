package com.cristhianbonilla.domain.model.payments.makePayment.request


data class TxValueModel(
    val currency: String?,
    val value: Int?
)