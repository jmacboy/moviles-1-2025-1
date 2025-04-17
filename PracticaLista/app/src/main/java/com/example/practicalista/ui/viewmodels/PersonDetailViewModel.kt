package com.example.practicalista.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicalista.models.Person
import com.example.practicalista.repositories.PersonRepository

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    val person: MutableLiveData<Person> = _person
    private val _personSaved: MutableLiveData<Person> = MutableLiveData(null)
    val personSaved: MutableLiveData<Person> = _personSaved

    fun loadPerson(id: Int) {
        _person.value = PersonRepository.getPersonById(id)
    }

    fun savePerson(savedPerson: Person) {
        PersonRepository.savePerson(
            savedPerson
        )
        _personSaved.value = savedPerson
    }
}