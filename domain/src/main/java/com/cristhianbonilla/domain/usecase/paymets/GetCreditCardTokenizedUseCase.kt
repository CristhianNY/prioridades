package com.cristhianbonilla.domain.usecase.paymets

import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCase.Params

interface GetCreditCardTokenizedUseCase : UseCase<Params, CreditCardTokenModel> {
    data class Params(val creditCardTokenRequestModel: CreditCardTokenRequestModel)
}