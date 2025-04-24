package com.example.practicaroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaroom.db.dao.PersonDao
import com.example.practicaroom.db.dao.PhoneDao
import com.example.practicaroom.db.migrations.Migration1To2
import com.example.practicaroom.db.migrations.Migration2To3
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.Phone

@Database(
    entities = [Person::class, Phone::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun phoneDao(): PhoneDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "prueba-db"
            )
                .addMigrations(
                    Migration1To2(),
                    Migration2To3()
                )
                .build()
        }
    }
}