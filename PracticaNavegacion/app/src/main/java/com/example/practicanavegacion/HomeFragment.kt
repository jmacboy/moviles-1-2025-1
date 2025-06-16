package com.example.practicanavegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practicanavegacion.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }


}