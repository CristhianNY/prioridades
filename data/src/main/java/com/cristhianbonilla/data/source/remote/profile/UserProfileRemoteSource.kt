package com.cristhianbonilla.data.source.remote.profile

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.profile.UserModel

interface UserProfileRemoteSource : RemoteSource {
    suspend fun getUserInformation(): CustomResult<Failure, UserModel>
}