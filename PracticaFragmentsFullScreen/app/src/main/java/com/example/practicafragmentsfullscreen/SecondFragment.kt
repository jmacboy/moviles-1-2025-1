package com.example.practicafragmentsfullscreen

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practicafragmentsfullscreen.databinding.FragmentSecondBinding
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {


    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        setupThreadAnimation()
        return binding.root
    }

    private fun setupThreadAnimation() {
        Thread {
            Thread.sleep(3000)
            activity?.runOnUiThread {
                Toast.makeText(context, "Pasaron 3 seg", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }
}