package com.cristhianbonilla.feature_login

import android.content.Context
import android.content.Intent
import com.cristhianbonilla.feature_login.login.LoginState
import com.cristhianbonilla.feature_magazine_reader.magazineViewer.ReaderMagazineActivity
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState

class   AuthenticationActivity :BaseActivity<LoginState>(R.layout.activity_login) {

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onStateChanged(state: LoginState) {
        super.onStateChanged(state)
        when (state) {
            LoginState.SuccessLogin -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }
}

open class RegisterState : BaseState