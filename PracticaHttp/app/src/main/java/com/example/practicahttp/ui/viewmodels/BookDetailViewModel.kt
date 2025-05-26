package com.example.practicahttp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicahttp.models.Book
import com.example.practicahttp.repositories.BookRepository
import kotlinx.coroutines.launch

class BookDetailViewModel : ViewModel() {
    private val _bookSaved: MutableLiveData<Book> = MutableLiveData(null)
    val bookSaved: MutableLiveData<Book> = _bookSaved
    fun saveBook(book: Book) {
        viewModelScope.launch {
            try {
                val theBook = BookRepository.createBook(book)
                _bookSaved.postValue(theBook)
            } catch (e: Exception) {
                Log.e("PostDetailViewModel", "Error loading post list", e)
            }

        }
    }
}