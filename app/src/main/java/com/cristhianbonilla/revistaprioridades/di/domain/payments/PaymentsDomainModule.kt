package com.cristhianbonilla.revistaprioridades.di.domain.payments

import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCase
import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCaseImpl
import com.cristhianbonilla.domain.usecase.paymets.MakePaymentTokenizedCardUseCase
import com.cristhianbonilla.domain.usecase.paymets.MakePaymentTokenizedCardUseCaseImpl
import org.koin.dsl.module

internal val paymentsDomainModule = module {
    factory<GetCreditCardTokenizedUseCase> {
        GetCreditCardTokenizedUseCaseImpl(paymentsRepository = get())
    }

    factory<MakePaymentTokenizedCardUseCase> {
        MakePaymentTokenizedCardUseCaseImpl(paymentsRepository = get())
    }
}