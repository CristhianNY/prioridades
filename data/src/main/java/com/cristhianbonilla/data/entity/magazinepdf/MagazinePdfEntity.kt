package com.cristhianbonilla.data.entity.magazinepdf


import com.cristhianbonilla.data.entity.home.MagazineEntity
import com.cristhianbonilla.data.entity.home.toModel
import com.cristhianbonilla.domain.model.home.MagazineModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MagazinePdfEntity(
    @Json(name = "Magazine")
    val magazine: MagazinePdfContentEntity?
)

fun MagazinePdfEntity.toModel(): MagazinePdfModel {
    return MagazinePdfModel(
        magazine?.toModel()
    )
}