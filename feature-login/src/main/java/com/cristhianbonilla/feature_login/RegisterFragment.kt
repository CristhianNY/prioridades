package com.cristhianbonilla.feature_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cristhianbonilla.feature_login.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.tvLoginTitle.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.go_to_login_fragment)
        }

        binding.btnNextRegister.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.go_to_finish_register_fragment)
        }
        return view
    }
}