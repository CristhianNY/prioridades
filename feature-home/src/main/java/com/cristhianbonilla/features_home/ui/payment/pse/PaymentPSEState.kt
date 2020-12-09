package com.cristhianbonilla.features_home.ui.payment.pse

import com.cristhianbonilla.features_home.HomeState

sealed class PaymentPSEState : HomeState() {
    object Error : PaymentPSEState()
    object Loading : PaymentPSEState()
    object ShowConfirmationPaymentDialog : PaymentPSEState()
}