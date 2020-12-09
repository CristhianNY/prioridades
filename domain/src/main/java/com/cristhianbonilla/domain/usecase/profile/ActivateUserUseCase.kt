package com.cristhianbonilla.domain.usecase.profile

import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.UseCase

interface ActivateUserUseCase: UseCase<UseCase.None, UserModel>