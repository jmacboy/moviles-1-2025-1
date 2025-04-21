package com.example.practicaroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaroom.db.dao.PersonDao
import com.example.practicaroom.db.models.Person

@Database(
    entities = [Person::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "prueba-db"
            ).build()
        }
    }
}