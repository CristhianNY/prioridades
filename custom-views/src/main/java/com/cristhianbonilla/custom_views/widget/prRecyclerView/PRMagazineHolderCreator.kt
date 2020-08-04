package com.cristhianbonilla.custom_views.widget.prRecyclerView

import android.view.ViewGroup

class PRMagazineHolderCreator(prMagazineHolderStrategy: PRMagazineHolderStrategy) {
    private val strategy = prMagazineHolderStrategy

    fun createViewHolder(parent:ViewGroup):PRMagazineViewHolder{
        return this.strategy.createViewHolder(parent)
    }
}