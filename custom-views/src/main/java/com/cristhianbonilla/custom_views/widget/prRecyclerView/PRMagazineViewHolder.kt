package com.cristhianbonilla.custom_views.widget.prRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_magazine_row.view.*
import java.lang.Exception

class PRMagazineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val MAGAZINE_TYPE = 2
    }

    fun bind(
        item: MagazineModelItem,
        listener: MagazineListener,
        position: Int,
        magazineItemListener: (MagazineModelItem) -> Unit
    ) {

        itemView.progressImageBar.visibility = View.VISIBLE
        Picasso.get().load(item.image).into(itemView.ivMagazine, object :com.squareup.picasso.Callback{
            override fun onSuccess() {
               itemView.progressImageBar.visibility = View.GONE
                itemView.monthOfMagazine.text = item.monthName.trim().capitalize()
            }

            override fun onError(e: Exception?) {
                itemView.monthOfMagazine.visibility = View.GONE
            }

        })

        itemView.ivMagazine.setOnClickListener {
            magazineItemListener.invoke(item)
        }
    }
}