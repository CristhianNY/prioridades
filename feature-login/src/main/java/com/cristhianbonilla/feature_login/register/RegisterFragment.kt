package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.feature_login.BR
import com.cristhianbonilla.feature_login.R
import com.cristhianbonilla.feature_login.databinding.FragmentRegisterBindingImpl
import com.cristhianbonilla.foundations.base.BaseFragment

class RegisterFragment : BaseFragment<
        RegisterUserState,
        RegisterData,
        RegisterTracker,
        RegisterViewModel,
        FragmentRegisterBindingImpl>(R.layout.fragment_register, BR.viewModel, BR.data)