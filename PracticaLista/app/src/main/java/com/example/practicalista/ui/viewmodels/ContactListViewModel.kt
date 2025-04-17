package com.example.practicalista.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicalista.models.Person
import com.example.practicalista.repositories.PersonRepository

class ContactListViewModel : ViewModel() {
    private val _peopleList: MutableLiveData<ArrayList<Person>> = MutableLiveData(arrayListOf())
    val peopleList: MutableLiveData<ArrayList<Person>> = _peopleList
    private val _personDeleted: MutableLiveData<Person> = MutableLiveData(null)
    val personDeleted: MutableLiveData<Person> = _personDeleted


    fun loadPeople() {
        _peopleList.value = PersonRepository.getPeople()
    }

    fun deletePerson(person: Person) {
        PersonRepository.deleteItem(person)
        _personDeleted.value = person
    }
}