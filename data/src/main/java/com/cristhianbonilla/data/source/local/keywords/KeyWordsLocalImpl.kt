package com.cristhianbonilla.data.source.local.keywords

import com.cristhianbonilla.data.entity.keywords.KeyWordEntity
import com.cristhianbonilla.data.entity.keywords.toModel
import com.cristhianbonilla.data.source.local.AssetJsonReader
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import retrofit2.Response

class KeyWordsLocalImpl(private val assetJsonReader: AssetJsonReader) : KeyWordsLocalSource {
    override suspend fun getKeyWords(): Result<Failure, KeyWordModel> {
        return request({
            val jsonfile: String = "keywords.json"

            Response.success(assetJsonReader.readJsonObject(jsonfile, KeyWordEntity::class.java))
        }, { entity, _ -> entity.toModel() })
    }
}