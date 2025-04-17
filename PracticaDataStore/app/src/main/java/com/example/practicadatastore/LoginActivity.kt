package com.example.practicadatastore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.practicadatastore.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.editText?.text.toString()
            val password = binding.txtPassword.editText?.text.toString()
            if (validateLogin(email, password)) {
                goToDashboard(email)
            } else {
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        val validUsers = mapOf(
            Pair("test@test.com", "123456"),
            Pair("admin@admin.com", "654321"),
            Pair("prueba@test.com", "123456")
        )
        return validUsers.containsKey(email) && validUsers[email] == password
    }

    private fun goToDashboard(email: String) {
        saveInfo(this, email)
        val dashboardIntent = DashboardActivity.intent(this)
        startActivity(dashboardIntent)
    }

    private fun saveInfo(context: Context, email: String) {
        val emailPref = stringPreferencesKey("emailValue")
        lifecycleScope.launch {
            context.dataStore.edit { settings ->
                settings[emailPref] = email
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}