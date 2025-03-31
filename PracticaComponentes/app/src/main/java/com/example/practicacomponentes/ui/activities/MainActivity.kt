package com.example.practicacomponentes.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacomponentes.R
import com.example.practicacomponentes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        binding.numberPicker2.value = 8
    }

    private fun setupEventListeners() {
        binding.btnShowValues.setOnClickListener { showValues() }
        binding.numberPicker1.setOnValueChangeListener {
            Toast.makeText(this, "Valor cambiado para picker 1: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showValues() {
        val picker1Value = binding.numberPicker1.value
        val picker2Value = binding.numberPicker2.value
        val picker3Value = binding.numberPicker3.value
        Toast.makeText(
            this,
            "valor 1: $picker1Value. Valor 2: $picker2Value. Valor 3: $picker3Value",
            Toast.LENGTH_LONG
        ).show()
    }
}