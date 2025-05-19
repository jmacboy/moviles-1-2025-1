package com.example.practicahttp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicahttp.R
import com.example.practicahttp.databinding.ActivityMainBinding
import com.example.practicahttp.models.Post
import com.example.practicahttp.repositories.PostsRepository
import com.example.practicahttp.ui.adapters.PostListAdapter
import com.example.practicahttp.ui.viewmodels.MainViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), PostListAdapter.OnPostClick {
    private val viewModel: MainViewModel by viewModels()
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
        viewModel.loadPostList()
    }

    private fun setupRecyclerView() {
        val adapter = PostListAdapter(arrayListOf())
        binding.rvPostList.apply {
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
        adapter.setOnPostClickListener(this)
    }

    private fun setupViewModelObservers() {
        viewModel.postList.observe(this) {
            val adapter = binding.rvPostList.adapter as PostListAdapter
            adapter.setData(it)
        }
    }

    override fun onPostClick(post: Post) {
        val intent = PostDetailActivity.newIntent(this, post.id)
        startActivity(intent)
    }

}