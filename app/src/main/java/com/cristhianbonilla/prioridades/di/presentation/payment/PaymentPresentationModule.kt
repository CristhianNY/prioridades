package com.cristhianbonilla.prioridades.di.presentation.payment

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.features_home.ui.payment.PaymentData
import com.cristhianbonilla.features_home.ui.payment.PaymentFragment
import com.cristhianbonilla.features_home.ui.payment.PaymentTracker
import com.cristhianbonilla.features_home.ui.payment.PaymentViewModel
import com.cristhianbonilla.features_home.ui.payment.mapper.MapperCreditCardRequest
import com.cristhianbonilla.features_home.ui.payment.pse.PaymentPSEData
import com.cristhianbonilla.features_home.ui.payment.pse.PaymentPSEFragment
import com.cristhianbonilla.features_home.ui.payment.pse.PaymentPSETracker
import com.cristhianbonilla.features_home.ui.payment.pse.PaymentPSEViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val paymentPresentationModule = module {
    scope(named<HomeActivity>()) {
    }

    scope(named<PaymentFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            PaymentViewModel(
                scope = get(),
                data = PaymentData(),
                tracker = get(),
                getCountriesUseCase = get(),
                getCreditCardTokenizedUseCase = get(),
                mapperCreditCardRequest = get(),
                getUserInformationUseCase = get(),
                makePaymentTokenizedCardUseCase = get(),
                activateUserUseCase = get()
            )
        }
        scoped { PaymentTracker() }
        scoped { MapperCreditCardRequest(androidContext()) }
    }

    scope(named<PaymentPSEFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            PaymentPSEViewModel(
                scope = get(),
                data = PaymentPSEData(),
                tracker = get(),
                activateUserUseCase = get()
            )
        }
        scoped { PaymentPSETracker() }
    }

}