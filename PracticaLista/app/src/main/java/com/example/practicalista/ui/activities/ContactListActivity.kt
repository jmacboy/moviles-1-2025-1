package com.example.practicalista.ui.activities

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
import com.example.practicalista.R
import com.example.practicalista.databinding.ActivityContactListBinding
import com.example.practicalista.models.Person
import com.example.practicalista.ui.adapters.PersonAdapter
import com.example.practicalista.ui.viewmodels.ContactListViewModel

class ContactListActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {
    private lateinit var binding: ActivityContactListBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                onActivityResult(result.data)
            }
        }
    private val viewModel: ContactListViewModel by viewModels()

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
        setupEventListeners()
        setupViewModelObservers()
        viewModel.loadPeople()
    }

    private fun setupViewModelObservers() {
        viewModel.peopleList.observe(this) {
            val adapter = binding.rvContactList.adapter as PersonAdapter
            adapter.setData(it)
        }
        viewModel.personDeleted.observe(this) {
            if (it == null) {
                return@observe
            }
            val adapter = binding.rvContactList.adapter as PersonAdapter
            adapter.deleteItem(it)
        }
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
        val adapter = binding.rvContactList.adapter as PersonAdapter
        adapter.updateItem(personSaved)
    }

    private fun personInserted(personSaved: Person?) {
        val adapter = binding.rvContactList.adapter as PersonAdapter
        adapter.insertItem(personSaved)
    }


    private fun setupEventListeners() {
        binding.btnCreatePerson.setOnClickListener {
            val createIntent = PersonDetailActivity.createIntent(this)
            resultLauncher.launch(createIntent)
        }
    }

    private fun setupRecyclerView() {

        val adapter = PersonAdapter(arrayListOf())
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

    override fun onPersonDetailClick(person: Person) {
        val intent = PersonDetailActivity.detailIntent(this, person)
        resultLauncher.launch(intent)
    }

    override fun onPersonDeleteClick(person: Person) {
        viewModel.deletePerson(person)
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