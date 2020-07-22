package com.cristhianbonilla.feature_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cristhianbonilla.feature_login.databinding.ActivityLoginBinding
import com.cristhianbonilla.foundations.base.BaseState

class   AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
}

open class RegisterState : BaseState