package com.cristhianbonilla.data.entity.magazinepdf


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Magazine(
    @Json(name = "id")
    val id: String?,
    @Json(name = "pdf")
    val pdf: String?
)