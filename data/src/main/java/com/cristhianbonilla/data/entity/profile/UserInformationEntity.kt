package com.cristhianbonilla.data.entity.profile


import com.cristhianbonilla.domain.model.profile.UserInformationModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInformationEntity(
    @Json(name = "apellidos")
    val apellidos: String? = null,
    @Json(name = "ciudad")
    val ciudad: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "fecha_fin")
    val fechaFin: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "nombres")
    val nombres: String? = null,
    @Json(name = "pais")
    val pais: String? = null,
    @Json(name = "telefono")
    val telefono: String? = null
)

fun UserInformationEntity.toModel() =
    UserInformationModel(
        apellidos,
        ciudad,
        email,
        fechaFin,
        id,
        nombres,
        pais,
        telefono
    )