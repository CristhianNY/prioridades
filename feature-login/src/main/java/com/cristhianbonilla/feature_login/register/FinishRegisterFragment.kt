package com.cristhianbonilla.feature_login.register

import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_login.BR
import com.cristhianbonilla.feature_login.R
import com.cristhianbonilla.feature_login.databinding.FragmentFinishRegisterBinding
import com.cristhianbonilla.foundations.base.BaseFragment

class FinishRegisterFragment : BaseFragment<
        RegisterUserState,
        RegisterData,
        RegisterTracker,
        RegisterViewModel,
        FragmentFinishRegisterBinding>(R.layout.fragment_finish_register, BR.viewModel, BR.data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}