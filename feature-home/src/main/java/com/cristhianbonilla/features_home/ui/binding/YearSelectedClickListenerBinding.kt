package com.cristhianbonilla.features_home.ui.binding

import com.cristhianbonilla.custom_views.widget.prradio.PRRadioGroupListener
import com.cristhianbonilla.features_home.ui.home.HomeData

class YearSelectedClickListenerBinding(
    prRadioGroupListener: PRRadioGroupListener? = null,
    year: HomeData? = null
){

    val onSelectCLickListener: (string:String) -> Unit = {
            prRadioGroupListener?.clickItem(it)
    }
}