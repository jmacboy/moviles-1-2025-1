package com.example.practicadatastore

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {
    val emailPref = stringPreferencesKey("emailValue")
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        waitXseconds()
    }

    private fun waitXseconds() {
        //run after 5 seconds
        lifecycleScope.launchWhenCreated {
            delay(5000)
            checkLogin()
        }

    }

    private fun checkLogin() {
        dataStore.data.map { preferences ->
            val email = preferences[emailPref]
            if (email != null) {
                val dashboardIntent = DashboardActivity.intent(this)
                startActivity(dashboardIntent)
            } else {
                val loginIntent = LoginActivity.intent(this)
                startActivity(loginIntent)
            }
        }
    }
}