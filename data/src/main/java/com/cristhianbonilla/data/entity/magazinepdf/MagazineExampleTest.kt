package com.cristhianbonilla.data.entity.magazinepdf


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MagazineExampleTest(
    @Json(name = "Magazine")
    val magazine: Magazine?
)