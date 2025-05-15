package com.example.practicahttp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicahttp.models.PostList
import com.example.practicahttp.repositories.PostsRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _postList: MutableLiveData<PostList> = MutableLiveData(arrayListOf())
    val postList: MutableLiveData<PostList> = _postList
    fun loadPostList(){
        viewModelScope.launch {
            try {
                val postList = PostsRepository.getPostList()
                _postList.postValue(postList)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading post list", e)
            }
        }
    }
}