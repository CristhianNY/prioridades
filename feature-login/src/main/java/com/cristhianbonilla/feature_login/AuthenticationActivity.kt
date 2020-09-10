package com.cristhianbonilla.feature_login

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import com.cristhianbonilla.feature_login.login.LoginFragmentDirections
import com.cristhianbonilla.feature_login.login.LoginState
import com.cristhianbonilla.feature_login.register.RegisterFragmentDirections
import com.cristhianbonilla.feature_login.register.RegisterUserState
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState

class AuthenticationActivity : BaseActivity<AuthenticationState>(R.layout.activity_login) {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onStateChanged(state: AuthenticationState) {
        super.onStateChanged(state)
        when (state) {
            LoginState.ErrorLogin -> {
                showInfoDialogError("Error", "Email o contraseña incorrectos", "Entrar")
            }
            LoginState.UserAlreadyLogged -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            LoginState.RegisterUser -> {
                innerNavigate(LoginFragmentDirections.goToRegisterFragment())
            }

            is RegisterUserState.NavigateToLogin->{
                innerNavigate(RegisterFragmentDirections.goToLoginFragment())
            }

            is RegisterUserState.NavigateToRegisterStep2 -> {
                innerNavigate(
                    RegisterFragmentDirections.goToFinishRegisterFragment(
                        state.names,
                        state.lastNames,
                        state.email,
                        state.password
                    )
                )
            }
            is RegisterUserState.Error -> {
                showInfoDialogError(
                    resources.getString(R.string.error),
                    resources.getString(R.string.error_register_user),
                    resources.getString(R.string.out)
                )
            }

            is RegisterUserState.UserAlreadyExist -> {
                showInfoDialogError(
                    resources.getString(R.string.error),
                    resources.getString(R.string.error_user_already_exist),
                    resources.getString(R.string.enter)
                )
            }

            is RegisterUserState.UserRegistrationSuccess -> {
                showInfoDialogSuccess(
                    resources.getString(R.string.great),
                    resources.getString(R.string.user_registered_success),
                    resources.getString(R.string.out)
                )
            }

            is LoginState.ForgotPassWord ->{
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://revistaprioridades.com/recordar-clave.php"))
                startActivity(browserIntent)
            }

            is RegisterUserState.NavigateToTermsAndConditions->{
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://tuiadpa.com/proteccion-de-datos-personales/"))
                startActivity(browserIntent)
            }
        }
    }

    private fun showInfoDialogError(title: String, message: String, positiveText: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(positiveText,
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun showInfoDialogSuccess(title: String, message: String, positiveText: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(positiveText,
                DialogInterface.OnClickListener { dialog, which ->
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(R.drawable.ic_checksucess).setCancelable(false)
            .show()
    }
}

open class AuthenticationState : BaseState