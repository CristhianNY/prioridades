package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class PRToolbar(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.Toolbar(context, attrs) {
    private var toolbarTitle: String? = null
    init {
        loadAttrs(attrs)
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.view_toolbar, this)
    }


    private fun loadAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PRToolbar,
            0, 0
        ).apply {
            toolbarTitle = getString(R.styleable.PRToolbar_toolbar_title)
            recycle()
        }
    }

    companion object {
        @BindingAdapter("toolbar_title")
        @JvmStatic
        fun setTitle(view: PRSpinner, toolbarTitle: String) {
            view.toolbarTitle.text = toolbarTitle
        }
    }
}