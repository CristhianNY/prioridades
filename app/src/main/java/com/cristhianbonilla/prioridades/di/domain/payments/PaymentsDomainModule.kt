package com.cristhianbonilla.prioridades.di.domain.payments

import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCase
import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCaseImpl
import org.koin.dsl.module

internal val paymentsDomainModule = module {
    factory<GetCreditCardTokenizedUseCase> {
        GetCreditCardTokenizedUseCaseImpl(paymentsRepository = get())
    }
}