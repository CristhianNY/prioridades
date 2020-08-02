package com.cristhianbonilla.features_home.ui.aboutUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cristhianbonilla.feature_home.R

class AboutUsFragment : Fragment() {

    private lateinit var notificationsViewModel: AboutUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(AboutUsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about_us, container, false)
        return root
    }
}