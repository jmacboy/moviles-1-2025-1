package com.example.practicaroom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.practicaroom.db.AppDatabase
import com.example.practicaroom.db.models.Person

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "prueba-db"
        )
            .allowMainThreadQueries() //TODO: quitar en produccion
            .build()

        db.personDao().insert(
            Person(
                "Juan",
                "Perez",
                "av 123",
                "1234568799",
                20
            )
        )
        val person = db.personDao().getPersonById(2)
        //TODO: Loggear los datos aqui
    }
}