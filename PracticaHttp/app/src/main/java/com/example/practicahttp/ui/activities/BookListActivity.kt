package com.example.practicahttp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicahttp.R
import com.example.practicahttp.databinding.ActivityBookListBinding
import com.example.practicahttp.databinding.ActivityMainBinding
import com.example.practicahttp.models.Book
import com.example.practicahttp.ui.adapters.BookListAdapter
import com.example.practicahttp.ui.viewmodels.BookListViewModel
import com.example.practicahttp.ui.viewmodels.MainViewModel

class BookListActivity : AppCompatActivity() {
    private val viewModel: BookListViewModel by viewModels()
    private lateinit var binding: ActivityBookListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelObservers()
        viewModel.loadBookList()
    }

    private fun setupRecyclerView() {
        val adapter = BookListAdapter(arrayListOf())
        binding.rvBookList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@BookListActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@BookListActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
//        adapter.setOnBookClickListener(this)
    }

    private fun setupViewModelObservers() {
        viewModel.postList.observe(this) {
            if(it == null) {
                return@observe
            }
            val adapter = binding.rvBookList.adapter as BookListAdapter
            adapter.setData(it)
        }
    }



}