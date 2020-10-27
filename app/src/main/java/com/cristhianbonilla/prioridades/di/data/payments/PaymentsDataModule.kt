package com.cristhianbonilla.prioridades.di.data.payments

import com.cristhianbonilla.data.repository.payments.PaymentsRepositoryImpl
import com.cristhianbonilla.data.source.remote.payment.PaymentRemoteSource
import com.cristhianbonilla.data.source.remote.payment.PaymentRemoteSourceImpl
import com.cristhianbonilla.data.source.remote.payment.api.PayULatamApi
import com.cristhianbonilla.data.source.remote.payment.mapper.PaymentRemoteMapper
import com.cristhianbonilla.data.source.remote.payment.mapper.PaymentRemoteMapperImpl
import com.cristhianbonilla.domain.repository.payments.PaymentsRepository
import com.cristhianbonilla.prioridades.di.data.REMOTE_PAYU_LATAM
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

internal val paymentDataModule = module {
    single<PayULatamApi> {
        get<Retrofit>(named(REMOTE_PAYU_LATAM)).create(
            PayULatamApi::class.java
        )
    }

    single<PaymentRemoteSource> {
        PaymentRemoteSourceImpl(
            payULatamApi = get(),
            mapper = get()
        )
    }

    single<PaymentsRepository> {
        PaymentsRepositoryImpl(
            paymentRemoteSource = get()
        )
    }

    single<PaymentRemoteMapper> {
        PaymentRemoteMapperImpl()
    }
}