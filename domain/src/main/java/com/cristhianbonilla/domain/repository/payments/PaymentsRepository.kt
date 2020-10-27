package com.cristhianbonilla.domain.repository.payments

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

interface PaymentsRepository {
    suspend fun tokenCreditCard(creditCardRequestModel: CreditCardTokenRequestModel): CustomResult<Failure, CreditCardTokenModel>
}