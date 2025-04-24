package com.example.practicaroom.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.repositories.PersonRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _peopleList: MutableLiveData<ArrayList<Person>> = MutableLiveData(arrayListOf())
    val peopleList: MutableLiveData<ArrayList<Person>> = _peopleList
    private val _personDeleted: MutableLiveData<Person> = MutableLiveData(null)
    val personDeleted: MutableLiveData<Person> = _personDeleted

    fun loadPeople(context: Context) {
        viewModelScope.launch {
            peopleList.postValue(
                PersonRepository.getPersonList(context) as ArrayList<Person>
            )
        }
    }

    fun deletePerson(context: Context, person: Person) {
        viewModelScope.launch {
            PersonRepository.deletePerson(context, person)
            _personDeleted.postValue(person)
        }
    }
}