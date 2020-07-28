package com.cristhianbonilla.foundations.navigation

import com.cristhianbonilla.foundations.base.BaseActivity

interface Navigator {

    fun navigate(params: NavParams)
    fun navigateForResult(activity: BaseActivity<*>, code: Int, params: NavParams)

}

open class NavParams(
    val type: NavType,
    val data: NavData? = null
)

interface NavType

interface NavData