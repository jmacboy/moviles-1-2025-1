package com.example.practicahttp.repositories

import com.example.practicahttp.models.Book
import com.example.practicahttp.models.BookList


object BookRepository {
    suspend fun getBookList(): BookList {
        return RetrofitRepository
            .getBookApi()
            .getBookList()
    }

    suspend fun getBook(id: Int): Book {
        return RetrofitRepository
            .getBookApi()
            .getBook(id)
    }
    suspend fun createBook(book: Book): Book {
        return RetrofitRepository
            .getBookApi()
            .createBook(book)
    }
}