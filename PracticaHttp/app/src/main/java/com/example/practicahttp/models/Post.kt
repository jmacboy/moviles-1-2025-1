package com.example.practicahttp.models

import com.google.gson.annotations.SerializedName

typealias PostList = ArrayList<Post>

data class Post (
    @SerializedName("userId")
    val userID: Int,
    val id: Int,
    val title: String,
    val body: String
)