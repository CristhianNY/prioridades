package com.cristhianbonilla.data.repository.profile

import com.cristhianbonilla.data.source.remote.profile.UserProfileRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.repository.profile.UserProfileRepository

class UserProfileRepositoryImpl(private val userProfileSource: UserProfileRemoteSource) : UserProfileRepository {
    override suspend fun getUserProfile(): Result<Failure, UserModel> {
        return userProfileSource.getUserInformation()
    }
}