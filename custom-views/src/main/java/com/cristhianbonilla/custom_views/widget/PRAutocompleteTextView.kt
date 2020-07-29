package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter

class PRAutocompleteTextView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatAutoCompleteTextView(context, attrs) {

    init {
        this.setPadding(0,0,0,0)
    }
    companion object {
        @BindingAdapter("key_words")
        @JvmStatic
        fun setKeyWords(view: PRAutocompleteTextView, listOfElements: List<String>) {
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, listOfElements)
            view.threshold = 0
            view.setAdapter(adapter)

        }
    }
}