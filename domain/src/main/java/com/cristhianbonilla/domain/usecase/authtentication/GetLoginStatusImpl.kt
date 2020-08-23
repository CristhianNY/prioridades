package com.cristhianbonilla.domain.usecase.authtentication

import com.cristhianbonilla.domain.repository.auth.AuthRepository

class GetLoginStatusImpl(private val authRepository: AuthRepository) : GetLoginStatus {
    override suspend fun isLogin() = authRepository.isLogged()
}
