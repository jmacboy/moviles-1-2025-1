package com.example.practicadatastore

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

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
        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        val emailPref = sharedPref.getString("email-pref", "")
        if (emailPref.isNullOrEmpty()) {
            val loginIntent = LoginActivity.intent(this)
            startActivity(loginIntent)
        } else {
            val dashboardIntent = DashboardActivity.intent(this)
            startActivity(dashboardIntent)
        }
    }
}