package com.cristhianbonilla.domain.repository.auth

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.UseCase

interface AuthRepository {

    suspend fun doLogin(
        userName: String,
        password: String
    ): CustomResult<Failure, UseCase.None>

    suspend fun doLogout(): CustomResult<Failure, UseCase.None>

    fun isLogged(): Boolean

    suspend fun doRegister(email:String,names:String,lastNames:String,country:String,city:String,phone:String,password: String):CustomResult<Failure,UserModel>
}