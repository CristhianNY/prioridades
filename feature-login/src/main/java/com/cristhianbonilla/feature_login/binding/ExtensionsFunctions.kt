package com.cristhianbonilla.feature_login.binding

import androidx.databinding.BindingAdapter
import com.rilixtech.widget.countrycodepicker.CountryCodePicker

@BindingAdapter("getCountryCode")
fun CountryCodePicker.getValue(listener: (String) -> Unit) {
    listener.invoke(this.fullNumber)
}
