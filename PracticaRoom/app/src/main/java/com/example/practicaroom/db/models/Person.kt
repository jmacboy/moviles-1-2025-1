package com.example.practicaroom.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Person(
    var name: String,
    var lastName: String,
    var address: String,
    var age: Int,
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}