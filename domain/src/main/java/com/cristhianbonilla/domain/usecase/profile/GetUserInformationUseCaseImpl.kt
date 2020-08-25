package com.cristhianbonilla.domain.usecase.profile

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.repository.profile.UserProfileRepository
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class GetUserInformationUseCaseImpl(private val repository: UserProfileRepository) : GetUserInformationUseCase {

    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ): Result<Failure, UserModel> {
        return repository.getUserProfile()
    }
}