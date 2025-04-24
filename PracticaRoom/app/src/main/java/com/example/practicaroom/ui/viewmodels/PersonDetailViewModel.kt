package com.example.practicaroom.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones
import com.example.practicaroom.db.models.Phone
import com.example.practicaroom.repositories.PersonRepository
import com.example.practicaroom.repositories.PhoneRepository
import kotlinx.coroutines.launch


class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<PersonWithPhones> = MutableLiveData(null)
    val person: MutableLiveData<PersonWithPhones> = _person
    private val _personSaved: MutableLiveData<Person> = MutableLiveData(null)
    val personSaved: MutableLiveData<Person> = _personSaved

    private val _phoneList: MutableLiveData<ArrayList<Phone>> = MutableLiveData(arrayListOf())
    val phoneList: MutableLiveData<ArrayList<Phone>> = _phoneList

    fun loadPerson(context: Context, id: Int) {
        viewModelScope.launch {
            _person.postValue(PersonRepository.getById(context, id))
        }
    }

    fun savePerson(context: Context, savedPerson: Person) {
        viewModelScope.launch {

            PersonRepository.savePerson(
                context,
                savedPerson
            )

            _personSaved.postValue(savedPerson)
        }
    }

    fun addPhone(context: Context, phone: Phone) {
        viewModelScope.launch {
            PhoneRepository.insertPhone(context, phone)
            val currentPhones = _phoneList.value ?: arrayListOf()
            currentPhones.add(phone)
            _phoneList.postValue(currentPhones)
        }

    }

    fun deletePhone(context: Context, phone: Phone) {
        viewModelScope.launch {
            PhoneRepository.deletePhone(context, phone)
            loadPerson(context, phone.personId)
        }
    }
}