package com.cristhianbonilla.data.source.remote.profile

import com.cristhianbonilla.data.entity.profile.toModel
import com.cristhianbonilla.data.source.remote.profile.api.UserInformationApi
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.profile.UserModel

class UserProfileRemoteSourceImpl(private val userInformationApi: UserInformationApi) : UserProfileRemoteSource{

    override suspend fun getUserInformation(): CustomResult<Failure, UserModel> {
        return request({
            userInformationApi.getUserInformation()
        }, { entity, _ -> entity.toModel() })
    }
}