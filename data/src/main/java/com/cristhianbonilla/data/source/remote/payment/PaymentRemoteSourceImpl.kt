package com.cristhianbonilla.data.source.remote.payment

import com.cristhianbonilla.data.source.remote.payment.api.PayULatamApi
import com.cristhianbonilla.data.source.remote.payment.mapper.PaymentRemoteMapper
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

class PaymentRemoteSourceImpl(
    private val payULatamApi: PayULatamApi,
    private val mapper: PaymentRemoteMapper
) : PaymentRemoteSource {
    override suspend fun getTokenCreditCard(creditCardRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel> {
        return request({
            payULatamApi.getCreditCardToken(
             mapper.toRequest(creditCardRequestModel)
            )
        }, { entity, _ -> mapper.toModel(entity) })
    }
}