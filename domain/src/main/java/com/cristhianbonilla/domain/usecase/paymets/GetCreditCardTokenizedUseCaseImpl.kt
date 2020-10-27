package com.cristhianbonilla.domain.usecase.paymets

import com.cristhianbonilla.domain.repository.payments.PaymentsRepository
import kotlin.coroutines.CoroutineContext

class GetCreditCardTokenizedUseCaseImpl(private val paymentsRepository: PaymentsRepository) :
    GetCreditCardTokenizedUseCase {

    override suspend fun invoke(
        params: GetCreditCardTokenizedUseCase.Params,
        context: CoroutineContext
    ) = paymentsRepository.tokenCreditCard(params.creditCardTokenRequestModel)
}