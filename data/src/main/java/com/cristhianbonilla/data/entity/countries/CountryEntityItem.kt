package com.cristhianbonilla.data.entity.countries


import com.cristhianbonilla.domain.model.countries.CountryItemModel
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryEntityItem(
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String
)

fun CountryEntityItem.toModel() =
    CountryItemModel(
        code.orEmpty(),
        name.orEmpty()
    )