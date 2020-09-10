package com.cristhianbonilla.features_home.ui.perfil

import android.widget.Toast
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.authtentication.DoLogoutUseCase
import com.cristhianbonilla.domain.usecase.profile.GetUserInformationUseCase
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute

class ProfileViewModel(
    scope: Scope,
    data: ProfileData,
    tracker: ProfileTracker,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val doLogoutUseCase: DoLogoutUseCase
) :
    BaseViewModel<ProfileState, ProfileData, ProfileTracker>(
        scope,
        data,
        tracker
    ) {
    val retry = {
        data.updateStateToLoginRequired()
    }
    fun getUserInformation() {
        data.loading()
        execute {

            getUserInformationUseCase(UseCase.None).fold(
                ::handleUserInformationFail,
                ::handleUserInformationSucces
            )
        }
    }

    fun goToRenew(){
        data.updateStateToRenewSubscription()
    }

    fun doLogout(){
        execute {
            doLogoutUseCase(UseCase.None).fold(
                ::handleErrorLogout,
                ::handleSuccesLogout
            )
        }

    }

    private fun handleSuccesLogout(none: UseCase.None) {
        data.updateStateToLoginRequired()
    }

    private fun handleErrorLogout(failure: Failure) {
        Toast.makeText(context, "Error al cerrar Sesión", Toast.LENGTH_LONG).show()
    }

    private fun handleUserInformationFail(failure: Failure) {
        data.error()
        when(failure){
            is Failure.SessionExpired ->{
               data.updateErrorMessage(R.string.login_error)
            }

            is Failure.SubscriptionNotActivated ->{
                data.updateErrorMessage(R.string.connection_error_text)
            }
        }
    }

    private fun handleUserInformationSucces(userModel: UserModel) {
        data.success()
        data.setFragmentContent(userModel)
    }

}