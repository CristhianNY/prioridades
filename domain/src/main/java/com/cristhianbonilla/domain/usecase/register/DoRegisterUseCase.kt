package com.cristhianbonilla.domain.usecase.register

import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.UseCase

interface DoRegisterUseCase: UseCase<DoRegisterUseCase.Params, UserModel> {

    data class Params(
        val name: String,
        val lastNames: String,
        val email : String,
        val password:String,
        val phone:String,
        val country:String,
        val city:String
    )
}