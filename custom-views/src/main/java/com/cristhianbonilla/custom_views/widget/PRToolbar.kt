package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import kotlinx.android.synthetic.main.view_pr_empty_state.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*

class PRToolbar(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.Toolbar(context, attrs) {
    private var mytoolbarTitle: String? = null
    private var toolbarType: Int? = null

    init {
        loadAttrs(attrs)
        initView()
        setStyle()
    }

    private fun setStyle() {
        when (toolbarType) {
            0 -> {
                this.ivGoBack.visibility = View.GONE
            }
            1 -> {
                this.logo_toolbar.visibility = View.GONE
                this.ivGoBack.visibility = View.INVISIBLE

            }
        }
    }

    private fun initView() {
        inflate(context, R.layout.view_toolbar, this)
    }

    fun setGoBackListener(action: () -> Unit): PRToolbar {
        ivGoBack.setOnClickListener { action() }
        return this
    }


    private fun loadAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PRToolbar,
            0, 0
        ).apply {
            toolbarType = getInt(R.styleable.PRToolbar_toolbar_type, 0)
            recycle()
        }
    }

    companion object {
        @BindingAdapter("title_custom_toolbar")
        @JvmStatic
        fun setTitle(view: PRToolbar, toolbarTitle: String) {
            view.toolbarTitle.text = toolbarTitle
        }


        @BindingAdapter("go_back_click")
        @JvmStatic
        fun onClickLinked(view: PRToolbar, onClickLink: () -> Unit) {
            view.setGoBackListener(onClickLink)
        }
    }

}