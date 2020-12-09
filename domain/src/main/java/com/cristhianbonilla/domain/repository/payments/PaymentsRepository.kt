package com.cristhianbonilla.domain.repository.payments

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.makePayment.request.MakePaymentRequestModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

interface PaymentsRepository {
    suspend fun tokenCreditCard(creditCardRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel>
    suspend fun makePayment(makePaymentRequestModel: MakePaymentRequestModel): CustomResult<Failure, MakePaymentResponseModel>
}