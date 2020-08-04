package com.cristhianbonilla.custom_views.widget.prRecyclerView

import com.cristhianbonilla.domain.model.home.MagazineModelItem

interface MagazineListener {
    fun magazineClick(position:Int,magazineItem:MagazineModelItem)
}