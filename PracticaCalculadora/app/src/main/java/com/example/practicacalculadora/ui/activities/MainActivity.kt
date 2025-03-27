package com.example.practicacalculadora.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicacalculadora.models.OperationType
import com.example.practicacalculadora.R
import com.example.practicacalculadora.databinding.ActivityMainBinding
import com.example.practicacalculadora.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

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
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.result.observe(this){ result ->
            if (result.isEmpty()) {
                binding.lblResult.text = "0"
            } else {
                binding.lblResult.text = result
            }
        }
    }

    private fun setupEventListeners() {
        binding.btn1.setOnClickListener { viewModel.addNumber(1) }
        binding.btn2.setOnClickListener { viewModel.addNumber(2) }
        binding.btn3.setOnClickListener { viewModel.addNumber(3) }
        binding.btn4.setOnClickListener { viewModel.addNumber(4) }
        binding.btn5.setOnClickListener { viewModel.addNumber(5) }
        binding.btn6.setOnClickListener { viewModel.addNumber(6) }
        binding.btn7.setOnClickListener { viewModel.addNumber(7) }
        binding.btn8.setOnClickListener { viewModel.addNumber(8) }
        binding.btn9.setOnClickListener { viewModel.addNumber(9) }
        binding.btn0.setOnClickListener { viewModel.addNumber(0) }
        binding.btnAddition.setOnClickListener { viewModel.startOperation(OperationType.ADDITION) }
        binding.btnSubtraction.setOnClickListener { viewModel.startOperation(OperationType.SUBTRACTION) }
        binding.btnMultiply.setOnClickListener { viewModel.startOperation(OperationType.MULTIPLICATION) }
        binding.btnDivide.setOnClickListener { viewModel.startOperation(OperationType.DIVISION) }
        binding.btnEquals.setOnClickListener { viewModel.calculateResult() }
        binding.btnClearOne.setOnClickListener { viewModel.clearOne() }
        binding.btnClearEverything.setOnClickListener { viewModel.clearEverything() }
    }

}