package com.cristhianbonilla.prioridades.platform.preferences

import android.content.Context
import com.cristhianbonilla.data.main.preference.Preferences
import android.content.SharedPreferences
import kotlin.reflect.KClass

class SharedPreferences(
    context: Context,
    id: String
) : Preferences {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            id,
            Context.MODE_PRIVATE
        )
    }

    override fun <T> setValue(
        key: String,
        value: T
    ) {
        val editor = prefs.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            is Int -> editor.putInt(key, value)
            else -> throw Exception("Unhandled return type")
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getValue(
        key: String,
        klass: KClass<T>
    ): T =
        when (klass) {
            Boolean::class -> prefs.getBoolean(key, false) as T
            Int::class -> prefs.getInt(key, 0) as T
            Float::class -> prefs.getFloat(key, 0f) as T
            String::class -> prefs.getString(key, "") as T
            Long::class -> prefs.getLong(key, 0) as T
            else -> throw Exception("Unhandled return type")
        }
}