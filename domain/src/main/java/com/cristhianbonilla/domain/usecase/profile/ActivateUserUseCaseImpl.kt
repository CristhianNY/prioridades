package com.cristhianbonilla.domain.usecase.profile

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.repository.profile.UserProfileRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class ActivateUserUseCaseImpl(private val repository: UserProfileRepository) : ActivateUserUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): CustomResult<Failure, UserModel> {
        return repository.activateUser()
    }
}