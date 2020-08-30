package com.cristhianbonilla.features_home.ui.aboutUs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        val btnEmail = root.findViewById<Button>(R.id.btn_send_email)
        btnEmail.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "soporte@revistaprioridades.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Soporte App")
            requireContext().startActivity(Intent.createChooser(emailIntent, null))
        }
        return root
    }
}