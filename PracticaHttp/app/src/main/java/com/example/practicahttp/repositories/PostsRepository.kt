package com.example.practicahttp.repositories

import com.example.practicahttp.models.Post
import com.example.practicahttp.models.PostList


object PostsRepository {
    suspend fun getPostList(): PostList {
        return RetrofitRepository
            .getJsonPlaceholderApi()
            .getPostList()
    }

    suspend fun getPost(id: Int): Post {
        return RetrofitRepository
            .getJsonPlaceholderApi()
            .getPost(id)
    }
}