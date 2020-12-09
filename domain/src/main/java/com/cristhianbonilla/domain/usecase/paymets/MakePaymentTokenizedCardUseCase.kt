package com.cristhianbonilla.domain.usecase.paymets

import com.cristhianbonilla.domain.model.payments.makePayment.request.MakePaymentRequestModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.paymets.MakePaymentTokenizedCardUseCase.Params

interface MakePaymentTokenizedCardUseCase : UseCase<Params, MakePaymentResponseModel> {
    data class Params(val makePaymentRequestModel: MakePaymentRequestModel)
}