package com.cristhianbonilla.data.entity.profile


import com.cristhianbonilla.domain.model.profile.UserModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntity(
    @Json(name = "User")
    val user: UserInformationEntity? = null
)

fun UserEntity?.toModel() =
    UserModel(
    this?.user?.toModel()
    )
