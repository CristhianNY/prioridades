package com.cristhianbonilla.data.source.local

import android.content.Context
import com.squareup.moshi.Moshi

class AssetJsonReader(private val context: Context) {
    fun <T> readJsonObject(jsonName: String, type: Class<T>): T? {
        val moshi = Moshi.Builder().build()
        val jsonfile: String = context.assets.open(jsonName)
            .bufferedReader(Charsets.UTF_8).use { it.readText() }
        val jsonAdapter = moshi.adapter(type)
        return jsonAdapter.fromJson(jsonfile)
    }
}