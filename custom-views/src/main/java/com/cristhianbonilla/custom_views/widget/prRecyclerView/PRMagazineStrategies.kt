package com.cristhianbonilla.custom_views.widget.prRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cristhianbonilla.custom_views.R

interface PRMagazineHolderStrategy {
    fun createViewHolder(parent: ViewGroup): PRMagazineViewHolder
}


class PRCurrentMagazineMonth:PRMagazineHolderStrategy{
    override fun createViewHolder(parent: ViewGroup): PRMagazineViewHolder {
       return PRMagazineViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_magazine_row , parent,false))
    }
}

class PRNormalMagazinePRStrategy:PRMagazineHolderStrategy{
    override fun createViewHolder(parent: ViewGroup): PRMagazineViewHolder {
        return PRMagazineViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_magazine_row , parent,false))
    }
}