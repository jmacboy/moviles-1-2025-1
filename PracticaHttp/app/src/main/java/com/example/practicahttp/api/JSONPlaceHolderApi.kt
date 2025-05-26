package com.example.practicahttp.api

import com.example.practicahttp.models.Post
import com.example.practicahttp.models.PostList
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONPlaceHolderApi {
    @GET("posts")
    suspend fun getPostList(): PostList

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @POST("posts")
    suspend fun createPost(post: Post): Post


}