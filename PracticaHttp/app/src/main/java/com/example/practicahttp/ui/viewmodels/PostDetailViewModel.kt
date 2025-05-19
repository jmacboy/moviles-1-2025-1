package com.example.practicahttp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicahttp.models.Post
import com.example.practicahttp.repositories.PostsRepository
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    private val _post: MutableLiveData<Post> = MutableLiveData(null)
    val post: MutableLiveData<Post> = _post
    fun loadPost(id: Int) {
        viewModelScope.launch {
            try {
                val post = PostsRepository.getPost(id)
                _post.postValue(post)
            } catch (e: Exception) {
                Log.e("PostDetailViewModel", "Error loading post list", e)
            }
        }
    }
}