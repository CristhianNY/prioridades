package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import androidx.databinding.BindingAdapter

class PRWebview(context: Context, attrs: AttributeSet?)  : WebView(context, attrs) {

    fun setUrl(url: String){

        this.setUrl(url)
    }

    companion object {
        @BindingAdapter("load_url")
        @JvmStatic
        fun setUrl(view: PRWebview, url: String) {
           view.setUrl(url)
        }
    }
}