package com.cristhianbonilla.custom_views.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import com.cristhianbonilla.custom_views.R
import com.cristhianbonilla.domain.model.countries.CountryItemModel
import com.cristhianbonilla.domain.model.countries.CountryModel

class PRSpinner( context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatSpinner(context, attrs) {

    init {
        loadAttrs(attrs)
    }

    private fun loadAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PRSpinnerView,
            0, 0
        ).apply {
            recycle()
        }
    }

    fun setElements( listOfElements: List<String>){
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, listOfElements)
        this.adapter = adapter
    }

    fun setCountries(listOfElements: List<CountryItemModel>){
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, listOfElements)
        this.adapter = adapter
    }



    companion object {
        @BindingAdapter("item_list")
        @JvmStatic
        fun getElements(view: PRSpinner, listOfElements: List<String>) {
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, listOfElements)
            view.adapter = adapter
        }

        @BindingAdapter("dialCodes")
        @JvmStatic
        fun seDialCode(view: PRSpinner, listOfElements: List<String>) {
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, listOfElements)
            view.adapter = adapter
        }

    }




}