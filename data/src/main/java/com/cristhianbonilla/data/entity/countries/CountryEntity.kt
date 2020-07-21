package com.cristhianbonilla.data.entity.countries


import com.cristhianbonilla.domain.model.countries.CountryModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CountryEntity(
    @Json(name = "Country")
    val countryList: List<CountryEntityItem>?
)

fun CountryEntity.toModel(): CountryModel {

    return CountryModel(
        countryList?.map {
            it.toModel()
        }.orEmpty()
    )
}
