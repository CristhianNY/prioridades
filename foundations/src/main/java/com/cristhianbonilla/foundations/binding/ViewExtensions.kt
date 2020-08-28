package com.cristhianbonilla.foundations.binding


import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("custom_visibility")
fun View.customVisibility(isVisible: Boolean) {

    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}


