package com.example.practicahttp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicahttp.R
import com.example.practicahttp.databinding.ActivityMainBinding
import com.example.practicahttp.databinding.ActivityPostDetailBinding
import com.example.practicahttp.ui.viewmodels.MainViewModel
import com.example.practicahttp.ui.viewmodels.PostDetailViewModel

class PostDetailActivity : AppCompatActivity() {
    private val viewModel: PostDetailViewModel by viewModels()
    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        val postId = intent.getIntExtra(PARAM_POST_ID, -1)
        viewModel.loadPost(postId)
    }

    private fun setupViewModelObservers() {
        viewModel.post.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.lblPostDetailTitle.text = it.title
            binding.lblPostDetailBody.text = it.body
        }
    }

    companion object {
        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, PostDetailActivity::class.java)
            intent.putExtra(PARAM_POST_ID, id)
            return intent
        }

        private const val PARAM_POST_ID = "POST_ID"
    }
}