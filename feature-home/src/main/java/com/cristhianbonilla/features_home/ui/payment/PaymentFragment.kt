package com.cristhianbonilla.features_home.ui.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.PaymentFragmentBinding
import com.cristhianbonilla.foundations.base.BaseFragment

class PaymentFragment  : BaseFragment<
        PaymentState,
        PaymentData,
        PaymentTracker,
        PaymentViewModel,
        PaymentFragmentBinding>(R.layout.payment_fragment, BR.viewModel, BR.data) {
    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.getCountries()
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}