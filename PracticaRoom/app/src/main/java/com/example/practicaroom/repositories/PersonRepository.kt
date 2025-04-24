package com.example.practicaroom.repositories

import android.content.Context
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones

object PersonRepository {
    suspend fun savePerson(context: Context, person: Person) {
        if (person.id == 0) {
            insertPerson(context, person)
        } else {
            updatePerson(context, person)
        }
    }

    suspend fun insertPerson(
        context: Context,
        person: Person
    ) {
        val db = AppDatabase.getInstance(context)
        db.personDao().insertPerson(person)
    }

    suspend fun getById(context: Context, id: Int): PersonWithPhones {
        val db = AppDatabase.getInstance(context)
        return db
            .personDao()
            .getPersonById(id)

    }

    suspend fun getPersonList(context: Context): List<Person> {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getAllPersons()
    }

    suspend fun updatePerson(context: Context, person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .updatePerson(person)
    }

    suspend fun deletePerson(context: Context, person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .deletePerson(person)
    }
}