package com.cristhianbonilla.data.entity.magazinepdf


import com.cristhianbonilla.data.entity.home.MagazineItemEntity
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfContentModel
import com.cristhianbonilla.domain.model.magazinepdf.MagazinePdfModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MagazinePdfContentEntity(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "pdf")
    val pdf: String? = null
)

fun MagazinePdfContentEntity.toModel() =
    MagazinePdfContentModel(
        id.orEmpty(),
        pdf.orEmpty())