package com.cristhianbonilla.data.source.remote.profile

import com.cristhianbonilla.data.entity.profile.UserEntity
import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.model.profile.UserModel

interface UserProfileRemoteSource : RemoteSource {
    suspend fun getUserInformation(): Result<Failure, UserModel>
}