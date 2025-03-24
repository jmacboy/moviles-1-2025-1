package com.example.practicacalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn0: Button
    private lateinit var btnAddition: Button
    private lateinit var btnSubtraction: Button
    private lateinit var btnMultiplication: Button
    private lateinit var btnDivision: Button
    private lateinit var btnEquals: Button
    private lateinit var btnClearOne: Button
    private lateinit var btnClearEverything: Button
    private lateinit var lblResult: TextView
    private var result: String = ""
    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lblResult = findViewById(R.id.lblResult)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn0 = findViewById(R.id.btn0)
        btnAddition = findViewById(R.id.btnAddition)
        btnSubtraction = findViewById(R.id.btnSubtraction)
        btnMultiplication = findViewById(R.id.btnMultiply)
        btnDivision = findViewById(R.id.btnDivide)
        btnEquals = findViewById(R.id.btnEquals)
        btnClearOne = findViewById(R.id.btnClearOne)
        btnClearEverything = findViewById(R.id.btnClearEverything)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btn1.setOnClickListener { addNumber(1) }
        btn2.setOnClickListener { addNumber(2) }
        btn3.setOnClickListener { addNumber(3) }
        btn4.setOnClickListener { addNumber(4) }
        btn5.setOnClickListener { addNumber(5) }
        btn6.setOnClickListener { addNumber(6) }
        btn7.setOnClickListener { addNumber(7) }
        btn8.setOnClickListener { addNumber(8) }
        btn9.setOnClickListener { addNumber(9) }
        btn0.setOnClickListener { addNumber(0) }
        btnAddition.setOnClickListener { startOperation(OperationType.ADDITION) }
        btnSubtraction.setOnClickListener { startOperation(OperationType.SUBTRACTION) }
        btnMultiplication.setOnClickListener { startOperation(OperationType.MULTIPLICATION) }
        btnDivision.setOnClickListener { startOperation(OperationType.DIVISION) }
        btnEquals.setOnClickListener { calculateResult() }
        btnClearOne.setOnClickListener { clearOne() }
        btnClearEverything.setOnClickListener { clearEverything() }
    }

    private fun clearEverything() {
        prevNumber = 0
        currentOperation = OperationType.NONE
        result = ""
        reloadScreen()
    }

    private fun clearOne() {
        if (result.isEmpty()) {
            return
        }
        result = result.dropLast(1)
        reloadScreen()
    }

    private fun calculateResult() {
        var secondNumber = 0
        if (result.isNotEmpty()) {
            secondNumber = result.toInt()
        }
        val operationResult = when (currentOperation) {
            OperationType.ADDITION -> prevNumber + secondNumber
            OperationType.SUBTRACTION -> prevNumber - secondNumber
            OperationType.MULTIPLICATION -> prevNumber * secondNumber
            OperationType.DIVISION -> {
                if (secondNumber != 0) {
                    prevNumber / secondNumber
                } else {
                    Toast.makeText(this, "Error, división por 0", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            OperationType.NONE -> return
        }
        result = operationResult.toString()
        currentOperation = OperationType.NONE
        prevNumber = 0
        reloadScreen()
    }

    private fun startOperation(operation: OperationType) {
        currentOperation = operation
        prevNumber = result.toInt()
        result = ""
        reloadScreen()
    }

    private fun addNumber(num: Int) {
        result += num
        reloadScreen()
    }

    private fun reloadScreen() {
        if (result.isEmpty()) {
            lblResult.text = "0"
        } else {
            lblResult.text = result
        }
    }
}