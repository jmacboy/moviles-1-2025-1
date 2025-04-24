package com.example.practicaroom.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Phone(
    var reference: String,
    var number: String,
    var personId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}