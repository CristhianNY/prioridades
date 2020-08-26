package com.cristhianbonilla.domain.usecase.register

import com.cristhianbonilla.domain.repository.auth.AuthRepository
import kotlin.coroutines.CoroutineContext

class DoRegisterUseCaseImpl(private val authRepository: AuthRepository) : DoRegisterUseCase {

    override suspend fun invoke(
        params: DoRegisterUseCase.Params,
        context: CoroutineContext
    ) = authRepository.doRegister(
        params.email,
        params.name,
        params.lastNames,
        params.country,
        params.city,
        params.phone,
        params.password
    )
}