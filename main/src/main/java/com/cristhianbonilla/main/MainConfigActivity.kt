package com.cristhianbonilla.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.features_home.HomeActivity
import org.koin.android.ext.android.get

class MainConfigActivity : AppCompatActivity() {
    private val mainViewModel = get<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_config)
        
        val background = object : Thread() {
            override fun run() {
                super.run()

                try {
                    Thread.sleep(5000)
                    goToOtherActivity()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    private fun goToOtherActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }
}