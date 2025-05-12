package com.example.practicaanimacion.observer

interface Observable {
    fun addObserver(observer: Observer)
    fun notifyObservers()
}