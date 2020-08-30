package com.cristhianbonilla.foundations.binding


import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("custom_visibility")
fun View.customVisibility(isVisible: Boolean) {

    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

@BindingAdapter("trim_content")
fun EditText.trim(trim: Boolean) {
    if (trim) {

        val tex = this.text.trim()
        this.setText(tex,TextView.BufferType.EDITABLE)
    }
}


