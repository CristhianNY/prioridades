package com.cristhianbonilla.domain.usecase.authtentication

import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.UseCase.None

interface GetLoginStatus : UseCase<None,None> {
    suspend fun isLogin(): Boolean
}