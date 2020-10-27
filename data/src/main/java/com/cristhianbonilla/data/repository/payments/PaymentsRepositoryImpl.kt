package com.cristhianbonilla.data.repository.payments

import com.cristhianbonilla.data.source.remote.payment.PaymentRemoteSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.repository.payments.PaymentsRepository

class PaymentsRepositoryImpl(private val paymentRemoteSource: PaymentRemoteSource) :
    PaymentsRepository {
    override suspend fun tokenCreditCard(creditCardTokenRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel> {
        return paymentRemoteSource.getTokenCreditCard(creditCardTokenRequestModel)
    }
}