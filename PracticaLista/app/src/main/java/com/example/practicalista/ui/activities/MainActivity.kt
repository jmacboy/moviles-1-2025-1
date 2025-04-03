package com.example.practicalista.ui.activities

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalista.R
import com.example.practicalista.databinding.ActivityMainBinding
import com.example.practicalista.ui.adapters.NamesAdapter

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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val names = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Alice Johnson",
            "Bob Brown",
            "Charlie Davis",
            "Diana Prince",
            "Ethan Hunt",
            "Fiona Apple",
            "George Clooney",
            "Hannah Montana",
            "John Doe",
            "Jane Smith",
            "Alice Johnson",
            "Bob Brown",
            "Charlie Davis",
            "Diana Prince",
            "Ethan Hunt",
            "Fiona Apple",
            "George Clooney",
            "Hannah Montana",
        )
        val adapter = NamesAdapter(names)

        binding.rvNameList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

    }
}