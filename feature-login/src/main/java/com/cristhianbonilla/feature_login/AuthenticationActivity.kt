package com.cristhianbonilla.feature_login

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
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
                showInfoDialog("Error", "Email o contraseña incorrectos", "Entrar")
            }
            LoginState.UserAlreadyLogged -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            LoginState.RegisterUser -> {
                innerNavigate(LoginFragmentDirections.goToRegisterFragment())
            }

            is RegisterUserState.NavigateToRegisterStep2 -> {
                innerNavigate(RegisterFragmentDirections.goToFinishRegisterFragment())
            }
        }
    }

    private fun showInfoDialog(title: String, message: String, positiveText: String) {
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
}

open class AuthenticationState : BaseState