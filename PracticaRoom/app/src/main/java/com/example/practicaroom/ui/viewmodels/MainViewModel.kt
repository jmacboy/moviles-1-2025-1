package com.example.practicaroom.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.repositories.PersonRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _peopleList: MutableLiveData<ArrayList<Person>> = MutableLiveData(arrayListOf())
    val peopleList: MutableLiveData<ArrayList<Person>> = _peopleList

    fun loadPeople(context: Context) {
        viewModelScope.launch {
            peopleList.postValue(
                PersonRepository.getPersonList(context) as ArrayList<Person>
            )
        }
    }
}