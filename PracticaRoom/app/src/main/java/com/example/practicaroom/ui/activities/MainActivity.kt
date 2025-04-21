package com.example.practicaroom.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.R
import com.example.practicaroom.databinding.ActivityMainBinding
import com.example.practicaroom.ui.adapters.PersonAdapter
import com.example.practicaroom.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewmodel: MainViewModel by viewModels()
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
        setupViewModelObservers()

        viewmodel.loadPeople(this)
    }

    private fun setupViewModelObservers() {
        viewmodel.peopleList.observe(this) {
            val adapter = binding.rvPersonList.adapter as PersonAdapter
            adapter.setData(it)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonAdapter(arrayListOf())
        binding.rvPersonList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
}