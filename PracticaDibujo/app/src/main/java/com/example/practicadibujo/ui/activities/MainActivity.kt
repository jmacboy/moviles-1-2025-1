package com.example.practicadibujo.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicadibujo.R
import com.example.practicadibujo.databinding.ActivityMainBinding
import com.example.practicadibujo.models.ShapeType

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
    }

    private fun setupEventListeners() {
        binding.btnLine.setOnClickListener {
            binding.board.setShape(ShapeType.LINE)
        }
        binding.btnSquare.setOnClickListener {
            binding.board.setShape(ShapeType.SQUARE)
        }
        binding.btnCircle.setOnClickListener {
            binding.board.setShape(ShapeType.CIRCLE)
        }

    }
}