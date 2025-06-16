package com.example.practicanavegacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.practicanavegacion.databinding.FragmentDashboardBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private val args: DashboardFragmentArgs by navArgs()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lblWelcome.text = "Bienvenido " + args.emailParam
        return binding.root
    }

}