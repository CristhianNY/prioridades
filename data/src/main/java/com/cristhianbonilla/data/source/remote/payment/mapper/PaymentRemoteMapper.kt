package com.cristhianbonilla.data.source.remote.payment.mapper

import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

interface PaymentRemoteMapper {
    fun toRequest(creditCardTokenRequestModel: CreditCardTokenRequestModel): CreditCardTokenRequest
    fun toModel(creditCardTokenEntity: CreditCardTokenEntity?): CreditCardTokenModel
}