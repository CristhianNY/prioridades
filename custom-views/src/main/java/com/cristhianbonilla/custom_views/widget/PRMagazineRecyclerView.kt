package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.cristhianbonilla.custom_views.R
import com.cristhianbonilla.custom_views.widget.prRecyclerView.MagazineAdapter
import com.cristhianbonilla.custom_views.widget.prRecyclerView.MagazineListener
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import kotlinx.android.synthetic.main.view_magazine_recyclerview.view.*

class PRMagazineRecyclerView(
    context: Context, attrs: AttributeSet?
) : ConstraintLayout(context, attrs),
    MagazineListener {

    private var magazineListener: MagazineListener? = null
    private var magazineAdapter: MagazineAdapter? = null
    private var magazineList: ArrayList<MagazineModelItem>? = null
    private lateinit var magazineItemListener: (MagazineModelItem) -> Unit

    init {
        magazineList = ArrayList()
        initView()
    }

    fun initAdapter() {
        magazineAdapter = MagazineAdapter(this, magazineItemListener)

    }

    fun initView() {
        inflate(context, R.layout.view_magazine_recyclerview, this)
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(context, NUM_GRIDS)
        layoutManager.spanSizeLookup = object :
            GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }
        rvMagazineList?.layoutManager = layoutManager
        rvMagazineList.adapter = magazineAdapter
    }

    override fun magazineClick(position: Int, magazineItem: MagazineModelItem) {
        magazineListener?.magazineClick(position, magazineItem)
    }

    companion object {
        const val NUM_GRIDS = 2

        @BindingAdapter("magazine_list")
        @JvmStatic
        fun setMagazineList(view: PRMagazineRecyclerView, list: ArrayList<MagazineModelItem>) {
            view.magazineAdapter?.submitMagazineList(list)
        }


        @BindingAdapter("magazine_item_click")
        @JvmStatic
        fun setMagazineClick(
            view: PRMagazineRecyclerView,
            listener: MagazineListener
        ) {
            view.magazineListener = listener
        }

        @BindingAdapter("item_clicked")
        @JvmStatic
        fun setItemCLicked(
            view: PRMagazineRecyclerView,
            listener: ((MagazineModelItem) -> Unit)?
        ) {

            if (listener != null) {
                view.magazineItemListener = listener
                view.initAdapter()
                view.setUpRecyclerView()
            }
        }
    }
}