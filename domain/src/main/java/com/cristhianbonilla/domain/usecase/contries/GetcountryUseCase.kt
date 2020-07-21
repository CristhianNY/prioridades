package com.cristhianbonilla.domain.usecase.contries

import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.usecase.UseCase

interface GetcountryUseCase : UseCase<UseCase.None,CountryModel>