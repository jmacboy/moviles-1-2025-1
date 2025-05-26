package com.example.practicahttp.repositories

import com.example.practicahttp.api.BooksApi
import com.example.practicahttp.api.JSONPlaceHolderApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun getJsonPlaceholderApi(): JSONPlaceHolderApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JSONPlaceHolderApi::class.java)
    }
    fun getBookApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl("https://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }
}