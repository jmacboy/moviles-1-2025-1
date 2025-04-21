package com.example.practicaroom.repositories

import android.content.Context
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Person

object PersonRepository {
    suspend fun insertPerson(
        context: Context,
        person: Person
    ) {
        val db = AppDatabase.getInstance(context)
        db.personDao().insertPerson(person)
    }

    suspend fun getById(context: Context, id: Int): Person {
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

    suspend fun update(context: Context, person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .updatePerson(person)
    }
    suspend fun deletePerson(context: Context, person: Person){
        AppDatabase
            .getInstance(context)
            .personDao()
            .deletePerson(person)
    }
}