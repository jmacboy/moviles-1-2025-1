package com.example.practicatemplates2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicatemplates2.ui.second.SecondFragment

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance())
                .commitNow()
        }
    }
}