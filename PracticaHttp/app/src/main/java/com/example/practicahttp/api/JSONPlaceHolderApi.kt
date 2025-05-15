package com.example.practicahttp.api

import com.example.practicahttp.models.PostList
import retrofit2.http.GET

interface JSONPlaceHolderApi {
    @GET("posts")
    suspend fun getPostList(): PostList

}