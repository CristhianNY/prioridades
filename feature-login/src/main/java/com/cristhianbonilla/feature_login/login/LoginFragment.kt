package com.cristhianbonilla.feature_login.login

import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_login.BR
import com.cristhianbonilla.feature_login.R
import com.cristhianbonilla.feature_login.databinding.FragmentLoginBinding

import com.cristhianbonilla.foundations.base.BaseFragment

class LoginFragment : BaseFragment<
        LoginState,
        LoginData,
        LoginTracker,
        LoginViewModel,
        FragmentLoginBinding>(R.layout.fragment_login, BR.viewModel, BR.data) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}