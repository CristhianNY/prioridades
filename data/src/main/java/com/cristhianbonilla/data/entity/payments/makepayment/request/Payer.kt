package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Payer(
    @Json(name = "contactPhone")
    val contactPhone: String?,
    @Json(name = "dniNumber")
    val dniNumber: String?,
    @Json(name = "emailAddress")
    val emailAddress: String?,
    @Json(name = "fullName")
    val fullName: String?,
    @Json(name = "merchantPayerId")
    val merchantPayerId: String?
)