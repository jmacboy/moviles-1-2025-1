package com.example.practicahttp.api

import com.example.practicahttp.models.Book
import com.example.practicahttp.models.BookDeleteResponse
import com.example.practicahttp.models.BookList
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface BooksApi {

    @GET("libros")
    suspend fun getBookList(): BookList

    @GET("libros/{id}")
    suspend fun getBook(@Path("id") id: Int): Book

    @POST("libros")
    suspend fun createBook(@Body post: Book): Book

    @PUT("libros/{id}")
    suspend fun updateBook(@Path("id") id: Int, post: Book): Book

    @DELETE("libros/{id}")
    suspend fun deleteBook(@Path("id") id: Int): BookDeleteResponse
}