package com.example.practicaroom.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones

@Dao
interface PersonDao {
    @Query("Select * from Person")
    suspend fun getAllPersons(): List<Person>

    @Insert
    suspend fun insertPerson(person: Person): Long

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("Select * from Person where id = :id")
    suspend fun getPersonById(id: Int): PersonWithPhones
}