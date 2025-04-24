package com.example.practicaroom.repositories

import android.content.Context
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Phone

object PhoneRepository {
    suspend fun insertPhone(context: Context, phone: Phone) {
        AppDatabase
            .getInstance(context)
            .phoneDao()
            .insertPhone(phone)
    }

    suspend fun deletePhone(context: Context, phone: Phone) {
        AppDatabase
            .getInstance(context)
            .phoneDao()
            .deletePhone(phone)
    }
}