package com.cristhianbonilla.data.source.remote.payment

import com.cristhianbonilla.data.source.remote.payment.api.PayULatamApi
import com.cristhianbonilla.data.source.remote.payment.mapper.PaymentRemoteMapper
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.makePayment.request.MakePaymentRequestModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel

class PaymentRemoteSourceImpl(
    private val payULatamApi: PayULatamApi,
    private val mapper: PaymentRemoteMapper
) : PaymentRemoteSource {
    override suspend fun getTokenCreditCard(creditCardRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel> {
        return request({
            payULatamApi.getCreditCardToken(
                mapper.toRequest(creditCardRequestModel)
            )
        }, { entity, _ ->
            mapper.toModel(entity)
        })
    }

    override suspend fun makePayment(makePaymentRequestModel: MakePaymentRequestModel): CustomResult<Failure, MakePaymentResponseModel> {
        return request({
            payULatamApi.makePayment(
                mapper.toRequest(makePaymentRequestModel)
            )
        }, { entity, _ ->
            mapper.toModel(entity)
        })
    }
}