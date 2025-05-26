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
import com.example.practicahttp.databinding.ActivityBookDetailBinding
import com.example.practicahttp.models.Book
import com.example.practicahttp.ui.viewmodels.BookDetailViewModel

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding
    private val viewModel: BookDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupEventListeners()
    }

    private fun setupViewModelObservers() {
        viewModel.bookSaved.observe(this) {
            if (it == null) {
                return@observe
            }
            finish()
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveBook.setOnClickListener {
            val book = Book(
                nombre = binding.txtBookName.editText?.text.toString(),
                autor = binding.txtBookAuthor.editText?.text.toString(),
                imagen = binding.txtBookImage.editText?.text.toString(),
                editorial = binding.txtBookEditorial.editText?.text.toString(),
                sinopsis = binding.txtBookSynopsis.editText?.text.toString(),
                isbn = binding.txtBookIsbn.editText?.text.toString()
            )
            viewModel.saveBook(book)
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BookDetailActivity::class.java)
        }
    }
}