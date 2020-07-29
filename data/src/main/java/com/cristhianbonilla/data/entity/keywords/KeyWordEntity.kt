package com.cristhianbonilla.data.entity.keywords

import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeyWordEntity(
    @Json(name = "KeyWords")
    val keyWordList: List<KeyWordItemEntity>?
)

fun KeyWordEntity.toModel(): KeyWordModel {

    return KeyWordModel(
        keyWordList?.map {
            it.toModel()
        }.orEmpty()
    )
}