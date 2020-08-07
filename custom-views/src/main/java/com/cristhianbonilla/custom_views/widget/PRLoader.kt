package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import kotlinx.android.synthetic.main.view_toolbar.view.*

class PRLoader(context: Context, attrs: AttributeSet?) : ProgressBar(context, attrs) {

    companion object {
        @BindingAdapter("loader_visibility")
        @JvmStatic
        fun setVisibility(view: PRLoader, isVisible: Boolean) {
            if(isVisible){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.GONE
            }
        }
    }
}