package com.cristhianbonilla.data.entity.home


import com.cristhianbonilla.domain.model.home.MagazineModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MagazineEntity(
    @Json(name = "MagazineList")
    val magazineItemEntityList: List<MagazineItemEntity>
)

fun MagazineEntity.toModel(): MagazineModel {
    return MagazineModel(
        magazineItemEntityList?.map {
            it.toModel()
        }.orEmpty()
    )
}