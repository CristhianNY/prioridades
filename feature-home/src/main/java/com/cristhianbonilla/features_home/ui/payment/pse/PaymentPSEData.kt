package com.cristhianbonilla.features_home.ui.payment.pse

import com.cristhianbonilla.foundations.base.BaseData

class PaymentPSEData :
    BaseData<PaymentPSEState>() {

    fun updateStateToRenewSubscription() {
        updateState(PaymentPSEState.ShowConfirmationPaymentDialog)
    }

}

