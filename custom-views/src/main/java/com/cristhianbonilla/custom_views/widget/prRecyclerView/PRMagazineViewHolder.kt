package com.cristhianbonilla.custom_views.widget.prRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_magazine_row.view.*

class PRMagazineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val MAGAZINE_TYPE = 2
    }

    fun bind(item: MagazineModelItem, listener: MagazineListener, position: Int) {
        itemView.monthOfMagazine.text = item.month
        Picasso.get().load(item.image).into(itemView.ivMagazine)
    }
}