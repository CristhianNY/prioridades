package com.cristhianbonilla.features_home.ui.payment.pse

import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.profile.ActivateUserUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.execute

class PaymentPSEViewModel(
    scope: Scope,
    data: PaymentPSEData,
    tracker: PaymentPSETracker,
    private val activateUserUseCase: ActivateUserUseCase
) : BaseViewModel<PaymentPSEState, PaymentPSEData, PaymentPSETracker>(scope, data, tracker) {

    init {
        activateSubscription()
    }
    private fun activateSubscription() {
        execute {
            data.showLoading
            activateUserUseCase(UseCase.None).fold(
                {
                    data.error()
                }, ::handleUserActiveSuccess
            )
        }
    }

    private fun handleUserActiveSuccess(userModel: UserModel) {
        data.updateStateToRenewSubscription()
    }

}