package com.example.practicaroom.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicaroom.db.models.Person

@Dao
interface PersonDao {
    @Query("Select * from Person")
    fun getAllPersons(): List<Person>

    @Query("Select * from Person where id = :id")
    fun getPersonById(id: Int): Person

    @Insert
    fun insert(person: Person): Long
}