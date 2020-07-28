package com.cristhianbonilla.feature_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cristhianbonilla.feature_login.databinding.ActivityLoginBinding
import com.cristhianbonilla.foundations.base.BaseActivity
import com.cristhianbonilla.foundations.base.BaseState

class   AuthenticationActivity : BaseActivity<RegisterState>(R.layout.activity_login, R.navigation.login_navigation, R.id.fragment_container) {


    
}

open class RegisterState : BaseState