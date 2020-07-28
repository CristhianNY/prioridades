package com.cristhianbonilla.foundations.extensions

import androidx.annotation.LayoutRes
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.interaction.Interaction
import com.cristhianbonilla.foundations.navigation.Navigator
import org.koin.android.ext.android.inject

internal fun BaseActivity<*>.injectInteraction() = inject<Interaction>()

internal inline fun <reified N : Navigator> BaseActivity<*>.injectNavigator() = inject<N>()

fun BaseActivity<*>.showTooltip(@LayoutRes resId: Int) {
}