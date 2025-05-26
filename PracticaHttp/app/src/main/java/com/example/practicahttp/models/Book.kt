package com.example.practicahttp.models

typealias BookList = ArrayList<Book>
data class Book(
    val autor: String,
    val calificacion: Int,
    val editorial: String,
    val generos: List<Genero>,
    val id: Int,
    val imagen: String,
    val isbn: String,
    val nombre: String,
    val sinopsis: String,
)