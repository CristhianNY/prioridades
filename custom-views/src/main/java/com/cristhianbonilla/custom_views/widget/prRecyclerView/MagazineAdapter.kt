package com.cristhianbonilla.custom_views.widget.prRecyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MagazineAdapter(private var mbMenuListener: MagazineListener) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var menuItemList: ArrayList<MagazineModelItem> = ArrayList()
    private var layoutStrategy:PRMagazineHolderStrategy? = null

    var viewTypesDictionary: HashMap<Int, PRMagazineHolderStrategy> =
        HashMap()

    companion object {
        const val CURRENT_MONTH_MAGAZINE = Calendar.MONTH
        const val LAYOUT_SIMPLE_ITEM = 1

    }

    init {
        viewTypesDictionary[CURRENT_MONTH_MAGAZINE] = PRCurrentMagazineMonth()
        viewTypesDictionary[LAYOUT_SIMPLE_ITEM] = PRNormalMagazinePRStrategy()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == CURRENT_MONTH_MAGAZINE){
           layoutStrategy = viewTypesDictionary.getValue(CURRENT_MONTH_MAGAZINE)
        }else{
            layoutStrategy = viewTypesDictionary.getValue(LAYOUT_SIMPLE_ITEM)
        }

        val mbMenuHolderStrategy =
            PRMagazineHolderCreator(
                layoutStrategy!!
            )
        return mbMenuHolderStrategy.createViewHolder(parent)
    }

    override fun getItemViewType(position: Int) = menuItemList[position].month.toInt()

    override fun getItemCount() = menuItemList.size

    fun submitMagazineList(magazineModelItemList: ArrayList<MagazineModelItem>) {
        menuItemList = magazineModelItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PRMagazineViewHolder -> {
                holder.bind(menuItemList[position], mbMenuListener, position)
            }
        }
    }
}