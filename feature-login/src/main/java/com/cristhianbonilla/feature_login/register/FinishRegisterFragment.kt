package com.cristhianbonilla.feature_login.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.afollestad.vvalidator.form
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

    private val args: FinishRegisterFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel.getCountries()
        viewModel.fillDataFromFragmentRegisterOne(
            args.names,
            args.lastNames,
            args.email,
            args.password
        )
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        form {
            input(R.id.editCity) {
                isNotEmpty().description(R.string.city_required)
            }

            input(R.id.editCity) {
                isNotEmpty().description(R.string.city_required)
            }

            submitWith(R.id.btn_finish_register) { result ->
                viewModel.registerUser()
            }
        }

    }

}