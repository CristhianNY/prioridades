package com.cristhianbonilla.domain.usecase.authtentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.repository.auth.AuthRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class DoLogoutUseCaseImpl(private val authRepository: AuthRepository):DoLogoutUseCase {

    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): Result<Failure, UseCase.None> {
        return authRepository.doLogout()
    }
}