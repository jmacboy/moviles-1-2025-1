package com.example.practicahttp.repositories

import com.example.practicahttp.api.JSONPlaceHolderApi
import com.example.practicahttp.models.PostList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PostsRepository {
    suspend fun getPostList(): PostList {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(JSONPlaceHolderApi::class.java)
        return service.getPostList()
    }
}