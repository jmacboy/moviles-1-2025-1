package com.example.practicanavegacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.practicanavegacion.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.txtEmailLogin.editText?.text.toString()
            val password = binding.txtPasswordLogin.editText?.text.toString()
            if (email == "admin@admin.com" && password == "admin") {
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(
                    email
                )
                findNavController().navigate(action)
            }
        }
    }

}