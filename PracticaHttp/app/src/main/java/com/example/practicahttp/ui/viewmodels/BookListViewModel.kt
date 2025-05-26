package com.example.practicahttp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicahttp.models.BookList
import com.example.practicahttp.repositories.BookRepository
import kotlinx.coroutines.launch

class BookListViewModel: ViewModel() {


    private val _postList: MutableLiveData<BookList> = MutableLiveData(arrayListOf())
    val postList: MutableLiveData<BookList> = _postList
    fun loadBookList(){
        viewModelScope.launch {
            try {
                val postList = BookRepository.getBookList()
                _postList.postValue(postList)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading post list", e)
            }
        }
    }
}