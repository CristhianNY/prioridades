package com.cristhianbonilla.feature_login.register

import android.os.Bundle
import android.view.View
import com.afollestad.vvalidator.form
import com.cristhianbonilla.feature_login.BR
import com.cristhianbonilla.feature_login.R
import com.cristhianbonilla.feature_login.databinding.FragmentRegisterBindingImpl
import com.cristhianbonilla.foundations.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment<
        RegisterUserState,
        RegisterData,
        RegisterTracker,
        RegisterViewModel,
        FragmentRegisterBindingImpl>(R.layout.fragment_register, BR.viewModel, BR.data){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        form {
            input(R.id.edit_name) {
                isNotEmpty().description(R.string.username_required)
            }
            input(R.id.editLastName) {
                isNotEmpty().description(R.string.last_name_required)
            }
            input(R.id.editEmail ) {
                isNotEmpty().description(R.string.email_required)
            }
            input(R.id.editPassword) {
                isNotEmpty().description(R.string.password_required)
            }
            input(R.id.editPassword){
               isNotEmpty()
            }

            input(R.id.editConfirmPassword){
                conditional({ R.id.editPassword != R.id.editConfirmPassword }){
                    assert("La contraseña no coincide ") { view -> false }

                }
            }
            submitWith(R.id.btnNextRegister) { result ->
            viewModel.goToRegisterStep2()
            }
        }

    }
}