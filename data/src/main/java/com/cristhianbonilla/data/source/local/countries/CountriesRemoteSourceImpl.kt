package com.cristhianbonilla.data.source.local.countries

import com.cristhianbonilla.data.entity.countries.CountryEntity
import com.cristhianbonilla.data.entity.countries.toModel
import com.cristhianbonilla.data.source.local.AssetJsonReader
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel
import retrofit2.Response

class CountriesRemoteSourceImpl(private val assetJsonReader: AssetJsonReader) :
    CountriesRemoteSource {
    override suspend fun getCountries(): Result<Failure, CountryModel> {
        return request({
            val jsonfile: String = "paises.json"

            Response.success(assetJsonReader.readJsonObject(jsonfile, CountryEntity::class.java))
        }, { entity, _ -> entity.toModel() })
    }
}