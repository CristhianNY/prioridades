package com.cristhianbonilla.features_home.ui.payment.pse

import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.PaymentFragmentBinding
import com.cristhianbonilla.foundations.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_pse.*

class PaymentPSEFragment : BaseFragment<
        PaymentPSEState,
        PaymentPSEData,
        PaymentPSETracker,
        PaymentPSEViewModel,
        PaymentFragmentBinding>(R.layout.fragment_pse, BR.viewModel, BR.data) {


}