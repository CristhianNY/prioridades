package com.cristhianbonilla.domain.repository.profile

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.profile.UserModel

interface UserProfileRepository {
    suspend fun getUserProfile(): Result<Failure, UserModel>
}