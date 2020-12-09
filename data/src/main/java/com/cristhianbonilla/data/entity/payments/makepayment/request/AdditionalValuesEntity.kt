package com.cristhianbonilla.data.entity.payments.makepayment.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalValuesEntity(
    @Json(name = "TX_VALUE")
    val tXVALUE: TxValueEntity?
)