package com.cristhianbonilla.domain.usecase.keywords

import com.cristhianbonilla.domain.model.keywords.KeyWordModel
import com.cristhianbonilla.domain.usecase.UseCase

interface GetKeyWordsUseCase : UseCase<UseCase.None, KeyWordModel>
