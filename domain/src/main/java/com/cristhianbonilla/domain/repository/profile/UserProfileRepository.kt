package com.cristhianbonilla.domain.repository.profile

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.profile.UserModel

interface UserProfileRepository {
    suspend fun getUserProfile(): CustomResult<Failure, UserModel>
}