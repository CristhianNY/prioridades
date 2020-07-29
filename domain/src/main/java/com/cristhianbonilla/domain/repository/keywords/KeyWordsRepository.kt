package com.cristhianbonilla.domain.repository.keywords

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.model.keywords.KeyWordModel

interface KeyWordsRepository {

    suspend fun getKeyWordsList(): Result<Failure, KeyWordModel>
}