package com.cristhianbonilla.domain.usecase.authtentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.repository.auth.AuthRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext
import com.cristhianbonilla.domain.functional.Result

class DoLoginUseCaseImpl(private val authRepository: AuthRepository) : DoLoginUseCase {

    override suspend fun invoke(
        params: DoLoginUseCase.Params,
        context: CoroutineContext
    ): Result<Failure, UseCase.None> {
        return authRepository.doLogin(params.userName, params.password)
    }
}