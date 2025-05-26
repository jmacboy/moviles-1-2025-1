package com.example.practicahttp.models

typealias BookList = ArrayList<Book>

data class Book(
    val autor: String,
    val editorial: String,
    val imagen: String,
    val isbn: String,
    val nombre: String,
    val sinopsis: String,
) {
    var id: Int = 0
    var calificacion: Int = 0
    var generos: List<Genero> = listOf()
}