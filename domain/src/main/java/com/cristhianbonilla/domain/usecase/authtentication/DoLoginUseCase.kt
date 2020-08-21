package com.cristhianbonilla.domain.usecase.authtentication

import com.cristhianbonilla.domain.usecase.UseCase
import java.security.Signature

interface DoLoginUseCase : UseCase<DoLoginUseCase.Params, UseCase.None> {

    data class Params(
        val userName: String,
        val password: String
    )
}