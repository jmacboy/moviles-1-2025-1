package com.example.practicaroom.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.practicaroom.db.models.Phone

@Dao
interface PhoneDao {
    @Insert
    suspend fun insertPhone(phone: Phone): Long
    @Delete
    suspend fun deletePhone(phone: Phone)
}