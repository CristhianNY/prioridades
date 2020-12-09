package com.cristhianbonilla.domain.usecase.paymets

import com.cristhianbonilla.domain.repository.payments.PaymentsRepository
import com.cristhianbonilla.domain.usecase.paymets.MakePaymentTokenizedCardUseCase.*
import kotlin.coroutines.CoroutineContext

class MakePaymentTokenizedCardUseCaseImpl(private val paymentsRepository: PaymentsRepository) :
    MakePaymentTokenizedCardUseCase {

    override suspend fun invoke(
        params: Params,
        context: CoroutineContext
    ) = paymentsRepository.makePayment(params.makePaymentRequestModel)
}