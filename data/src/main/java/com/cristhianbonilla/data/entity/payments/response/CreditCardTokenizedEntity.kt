package com.cristhianbonilla.data.entity.payments.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditCardTokenizedEntity(
    @Json(name = "creationDate")
    val creationDate: String?,
    @Json(name = "creditCardTokenId")
    val creditCardTokenId: String?,
    @Json(name = "errorDescription")
    val errorDescription: String?,
    @Json(name = "expirationDate")
    val expirationDate: String?,
    @Json(name = "identificationNumber")
    val identificationNumber: String?,
    @Json(name = "maskedNumber")
    val maskedNumber: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "number")
    val number: String?,
    @Json(name = "payerId")
    val payerId: String?,
    @Json(name = "paymentMethod")
    val paymentMethod: String?
)