package com.cristhianbonilla.feature_login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.cristhianbonilla.feature_login.databinding.FragmentFinishRegisterBinding
import com.cristhianbonilla.feature_login.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.toolbar.view.*

class FinishRegisterFragment : Fragment() {
    lateinit var binding:FragmentFinishRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishRegisterBinding.inflate(inflater,container,false)
        val view = binding.root

        val toolbar = view.my_toolbar
        view.ivGoBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
        return view
    }
}