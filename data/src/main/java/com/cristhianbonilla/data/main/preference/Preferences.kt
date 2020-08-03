package com.cristhianbonilla.data.main.preference

import kotlin.reflect.KClass

interface Preferences {
    fun <T> setValue(
        key: String,
        value: T
    )

    fun <T : Any> getValue(
        key: String,
        klass: KClass<T>
    ): T
}