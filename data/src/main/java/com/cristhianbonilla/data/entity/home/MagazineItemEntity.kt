package com.cristhianbonilla.data.entity.home


import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MagazineItemEntity(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "month")
    val month: String,
    @Json(name = "title")
    val title: String
)

fun MagazineItemEntity.toModel() =
    MagazineModelItem(
        description,
        id,
        image,
        month,
        title
    )