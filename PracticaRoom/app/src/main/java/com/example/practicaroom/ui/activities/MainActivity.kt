package com.example.practicaroom.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.R
import com.example.practicaroom.databinding.ActivityMainBinding
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.ui.adapters.PersonAdapter
import com.example.practicaroom.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                onActivityResult(result.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModelObservers()
        setupEventListeners()

        viewModel.loadPeople(this)
    }

    private fun onActivityResult(data: Intent?) {
        val personSaved = data?.getSerializableExtra(PARAM_PERSON_SAVED) as Person?
        val isInserted = data?.getBooleanExtra(PARAM_INSERTED, false) ?: false
        if (isInserted) {
            personInserted(personSaved)
        } else {
            personUpdated(personSaved)
        }
    }

    private fun personUpdated(personSaved: Person?) {
        val adapter = binding.rvPersonList.adapter as PersonAdapter
        adapter.updateItem(personSaved)
    }

    private fun personInserted(personSaved: Person?) {
        val adapter = binding.rvPersonList.adapter as PersonAdapter
        adapter.insertItem(personSaved)
    }

    private fun setupEventListeners() {
        binding.btnCreatePerson.setOnClickListener {
            val createIntent = PersonDetailActivity.createIntent(this)
            resultLauncher.launch(createIntent)
        }
    }
    private fun setupViewModelObservers() {
        viewModel.peopleList.observe(this) {
            val adapter = binding.rvPersonList.adapter as PersonAdapter
            adapter.setData(it)
        }
        viewModel.personDeleted.observe(this) {
            if (it == null) {
                return@observe
            }
            val adapter = binding.rvPersonList.adapter as PersonAdapter
            adapter.deleteItem(it)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonAdapter(arrayListOf())
        binding.rvPersonList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter.setOnPersonClickListener(this)
    }

    override fun onPersonClick(person: Person) {
        Toast.makeText(this, "Click en persona con id: ${person.id}", Toast.LENGTH_SHORT).show()
    }

    override fun onPersonDetailClick(person: Person) {
        val intent = PersonDetailActivity.detailIntent(this, person)
        resultLauncher.launch(intent)
    }

    override fun onPersonDeleteClick(person: Person) {
        viewModel.deletePerson(this, person)
    }

    companion object {
        private const val PARAM_PERSON_SAVED = "person_saved"
        private const val PARAM_INSERTED = "inserted"
        fun returnIntent(savedPerson: Person, isInserted: Boolean): Intent {
            return Intent().apply {
                putExtra(PARAM_PERSON_SAVED, savedPerson)
                putExtra(PARAM_INSERTED, isInserted)
            }
        }
    }
}