package com.cristhianbonilla.data.source.remote.payment

import com.cristhianbonilla.data.source.remote.source.RemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

interface PaymentRemoteSource: RemoteSource {

    suspend fun getTokenCreditCard(creditCardRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel>
}