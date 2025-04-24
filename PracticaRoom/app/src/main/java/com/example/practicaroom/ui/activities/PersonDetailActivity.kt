package com.example.practicaroom.ui.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.R
import com.example.practicaroom.databinding.ActivityMainBinding
import com.example.practicaroom.databinding.ActivityPersonDetailBinding
import com.example.practicaroom.databinding.PhoneAddDialogBinding
import com.example.practicaroom.db.models.Person
import com.example.practicaroom.db.models.PersonWithPhones
import com.example.practicaroom.db.models.Phone
import com.example.practicaroom.ui.adapters.PersonAdapter
import com.example.practicaroom.ui.adapters.PhoneAdapter
import com.example.practicaroom.ui.viewmodels.PersonDetailViewModel

class PersonDetailActivity : AppCompatActivity(), PhoneAdapter.PhoneClickListener {
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
        setupPhoneRecyclerView()
        setupViewModelObservers()
        person?.id?.let { viewModel.loadPerson(this, it) }
    }

    private fun setupPhoneRecyclerView() {
        val adapter = PhoneAdapter(arrayListOf())
        binding.rvPhoneList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonDetailActivity).apply {
                orientation = RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@PersonDetailActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        adapter.setOnPhoneClickListener(this)
    }

    private fun setupViewModelObservers() {
        viewModel.person.observe(this) {
            loadPersonDetails(it)
        }
        viewModel.personSaved.observe(this) {
            if (it == null) {
                return@observe
            }
            setResult(RESULT_OK, MainActivity.returnIntent(it, person?.id == null))
            finish()
        }
        viewModel.phoneList.observe(this) {
            val adapter = binding.rvPhoneList.adapter as PhoneAdapter
            adapter.setData(it)
        }
    }

    private fun setupEventListeners() {
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnSavePerson.setOnClickListener { savePerson() }
        binding.btnAddPhones.setOnClickListener { showPhoneDialog() }
    }


    private fun savePerson() {
        val savedPerson = Person(
            binding.txtName.editText?.text.toString(),
            binding.txtLastName.editText?.text.toString(),
            binding.txtAddress.editText?.text.toString(),
            binding.txtAge.editText?.text.toString().toInt(),
        )
        if (person != null) {
            savedPerson.id = person?.id!!
        }
        viewModel.savePerson(this, savedPerson)

    }

    private fun loadPersonDetails(personPhone: PersonWithPhones?) {
        if (personPhone == null) {
            return
        }
        binding.txtName.editText?.setText(personPhone.person.name)
        binding.txtLastName.editText?.setText(personPhone.person.lastName)
        binding.txtAge.editText?.setText(personPhone.person.age.toString())
        binding.txtAddress.editText?.setText(personPhone.person.address)
        val adapter = binding.rvPhoneList.adapter as PhoneAdapter
        adapter.setData(personPhone.phones as ArrayList<Phone>)
    }

    private fun showPhoneDialog() {
        val dialogBinding = PhoneAddDialogBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Agregar teléfono")
            .setView(dialogBinding.root)
            .setPositiveButton("Aceptar") { _, _ ->
                val phoneNumber = dialogBinding.txtPhoneAdd.editText?.text.toString()
                val reference = dialogBinding.spPhoneReference.selectedItem.toString()
                val phone = Phone(
                    reference,
                    phoneNumber,
                    person?.id ?: 0
                )
                if (phoneNumber == null || phoneNumber.isEmpty()) {
                    Toast.makeText(this, "El teléfono es requerido", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                viewModel.addPhone(this@PersonDetailActivity, phone)

            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
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

    override fun onPhoneDeleteClick(phone: Phone) {
        viewModel.deletePhone(this, phone)
    }
}