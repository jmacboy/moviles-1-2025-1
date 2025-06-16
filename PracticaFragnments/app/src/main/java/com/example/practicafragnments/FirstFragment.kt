package com.example.practicafragnments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practicafragnments.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnShowToast.setOnClickListener {
            Toast.makeText(
                context,
                "Hola desde el primer fragment",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}