package com.cristhianbonilla.feature_login.login

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.cristhianbonilla.feature_login.BR
import com.cristhianbonilla.feature_login.R
import com.cristhianbonilla.feature_login.databinding.FragmentLoginBinding

import com.cristhianbonilla.foundations.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<
        LoginState,
        LoginData,
        LoginTracker,
        LoginViewModel,
        FragmentLoginBinding>(R.layout.fragment_login, BR.viewModel, BR.data) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvRegisterTitle?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.go_to_register_fragment)
        }

    }
}