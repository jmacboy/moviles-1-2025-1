package com.example.practicaintents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var btnSendData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtName = findViewById(R.id.txtName)
        btnSendData = findViewById(R.id.btnSendData)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnSendData.setOnClickListener {
            val theName = txtName.text.toString()
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("varName", theName)
            startActivity(intent)
        }
    }
}