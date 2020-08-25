package com.cristhianbonilla.features_home.ui.perfil

import android.widget.Toast
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.profile.GetUserInformationUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute

class ProfileViewModel(
    scope: Scope,
    data: ProfileData,
    tracker: ProfileTracker,
    private val getUserInformationUseCase: GetUserInformationUseCase
) :
    BaseViewModel<ProfileState, ProfileData, ProfileTracker>(
        scope,
        data,
        tracker
    ) {

    fun getUserInformation() {
        data.loading()
        execute {

            getUserInformationUseCase(UseCase.None).fold(
                ::handleUserInformationFail,
                ::handleUserInformationSucces
            )
        }
    }

    private fun handleUserInformationFail(failure: Failure) {
        Toast.makeText(context,"Error Al traer Información del Usuario",Toast.LENGTH_LONG).show()
    }

    private fun handleUserInformationSucces(userModel: UserModel) {
        data.success()
        data.setFragmentContent(userModel)
    }
}