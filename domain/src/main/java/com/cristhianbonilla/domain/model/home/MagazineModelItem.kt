package com.cristhianbonilla.domain.model.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MagazineModelItem(
    val description: String,
    val id: String,
    val image: String,
    val month: String,
    val title: String,
    val monthName:String
):Parcelable