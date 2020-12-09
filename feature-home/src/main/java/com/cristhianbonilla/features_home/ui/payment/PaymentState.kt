package com.cristhianbonilla.features_home.ui.payment

import com.cristhianbonilla.features_home.HomeState

sealed class PaymentState : HomeState() {
    object Error : PaymentState()
    object Loading : PaymentState()
    object Success : PaymentState()
    object PayWIthPSE : PaymentState()
    object UserActivated : PaymentState()
}