package com.cristhianbonilla.data.entity.keywords

import com.cristhianbonilla.domain.model.keywords.KeyWordItemModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeyWordItemEntity(
    @Json(name = "name")
    val name: String
)

fun KeyWordItemEntity.toModel() =
    KeyWordItemModel(
        name.orEmpty()
    )