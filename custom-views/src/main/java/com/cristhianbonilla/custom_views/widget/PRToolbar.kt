package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import kotlinx.android.synthetic.main.view_toolbar.view.*

class PRToolbar(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.Toolbar(context, attrs) {
    private var toolbarTitle: String? = null
    private var toolbarType: Int? = null
    init {
        loadAttrs(attrs)
        initView()
        setStyle()

    }

    private fun setStyle() {
      when(toolbarType){
          0 ->{
              this.ivGoBack.visibility = View.GONE
          }
          1->{
              this.logo_toolbar.visibility = View.GONE
              this.ivGoBack.visibility = View.GONE
          }
          2 ->{
              this.logo_toolbar.visibility = View.GONE
          }
      }
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
            toolbarType = getResourceId(R.styleable.PRToolbar_toolbar_type, 0)
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