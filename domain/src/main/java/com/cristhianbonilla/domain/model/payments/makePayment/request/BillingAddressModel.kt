package com.cristhianbonilla.domain.model.payments.makePayment.request

data class BillingAddressModel(
    val city: String?,
    val country: String?,
    val phone: String?,
    val postalCode: String?,
    val state: String?,
    val street1: String?,
    val street2: String?
)