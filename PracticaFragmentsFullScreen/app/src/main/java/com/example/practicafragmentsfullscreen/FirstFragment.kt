package com.example.practicafragmentsfullscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicafragmentsfullscreen.databinding.FragmentFirstBinding


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
        binding.btnChangeFragments.setOnClickListener {
            val theFragment = parentFragmentManager.findFragmentById(R.id.main)
           val fragmentTransaction = parentFragmentManager.beginTransaction()
            if (theFragment is FirstFragment) {
                val secondFragment = SecondFragment.newInstance()
                fragmentTransaction.replace(R.id.main, secondFragment)
            } else {
                val firstFragment = FirstFragment()
                fragmentTransaction.replace(R.id.main, firstFragment)
            }
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


}