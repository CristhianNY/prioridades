package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BillingAddress(
    @Json(name = "city")
    val city: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "postalCode")
    val postalCode: String?,
    @Json(name = "state")
    val state: String?,
    @Json(name = "street1")
    val street1: String?,
    @Json(name = "street2")
    val street2: String?
)