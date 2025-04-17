package com.example.practicalista.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicalista.R
import com.example.practicalista.databinding.ActivityPersonDetailBinding
import com.example.practicalista.models.Person
import com.example.practicalista.ui.viewmodels.PersonDetailViewModel

class PersonDetailActivity : AppCompatActivity() {
    private var person: Person? = null
    private lateinit var binding: ActivityPersonDetailBinding
    private val viewModel: PersonDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        person = intent.getSerializableExtra(PARAM_PERSON) as Person?

        setupEventListeners()
        setupViewModelObservers()
        person?.id?.let { viewModel.loadPerson(it) }
    }

    private fun setupViewModelObservers() {
        viewModel.person.observe(this) {
            loadPersonDetails(it)
        }
        viewModel.personSaved.observe(this) {
            if (it == null) {
                return@observe
            }
            setResult(RESULT_OK, ContactListActivity.returnIntent(it, person?.id == null))
            finish()
        }
    }

    private fun setupEventListeners() {
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSavePerson.setOnClickListener { savePerson() }
    }

    private fun savePerson() {
        val savedPerson = Person(
            person?.id ?: 0,
            binding.txtName.editText?.text.toString(),
            binding.txtLastName.editText?.text.toString(),
            binding.txtPhone.editText?.text.toString(),
            binding.txtEmail.editText?.text.toString(),
            binding.txtAddress.editText?.text.toString()
        )
        viewModel.savePerson(savedPerson)

    }

    private fun loadPersonDetails(person: Person?) {
        if (person == null) {
            return
        }
        binding.txtName.editText?.setText(person.name)
        binding.txtLastName.editText?.setText(person.lastName)
        binding.txtPhone.editText?.setText(person.phone)
        binding.txtEmail.editText?.setText(person.email)
        binding.txtAddress.editText?.setText(person.address)
    }

    companion object {
        const val PARAM_PERSON = "person"

        fun detailIntent(context: Context, person: Person): Intent {
            val intent = Intent(context, PersonDetailActivity::class.java)
            intent.putExtra(PARAM_PERSON, person)
            return intent
        }

        fun createIntent(context: Context): Intent {
            return Intent(context, PersonDetailActivity::class.java)
        }
    }
}