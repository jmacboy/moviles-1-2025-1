package com.example.practicalista.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalista.R
import com.example.practicalista.databinding.ActivityContactListBinding
import com.example.practicalista.models.Person
import com.example.practicalista.ui.adapters.PersonAdapter

class ContactListActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {
    private lateinit var binding: ActivityContactListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val people = arrayListOf(
            Person(1, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
            Person(2, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
            Person(3, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
            Person(4, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
            Person(5, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
            Person(6, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
            Person(7, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
            Person(8, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
            Person(9, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
            Person(10, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
            Person(11, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
            Person(12, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
            Person(13, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
            Person(14, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
            Person(15, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
        )
        val adapter = PersonAdapter(people)
        binding.rvContactList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ContactListActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@ContactListActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter.setOnPersonClickListener(this)
    }

    override fun onPersonClick(person: Person) {
        Toast.makeText(this, "Click en persona con id: ${person.id}", Toast.LENGTH_SHORT).show()
    }
}