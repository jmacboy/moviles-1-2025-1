package com.example.practicaroom.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.databinding.PersonItemLayoutBinding
import com.example.practicaroom.db.models.Person

class PersonAdapter(
    var people: ArrayList<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    var personClickListener: PersonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonItemLayoutBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = people[position]
        holder.bind(item, personClickListener)
    }

    fun setOnPersonClickListener(listener: PersonClickListener) {
        personClickListener = listener
    }

    fun setData(people: java.util.ArrayList<Person>) {
        this.people = people
        notifyDataSetChanged()
    }

    fun updateItem(personSaved: Person?) {
        val index = people.indexOfFirst { it.id == personSaved?.id }
        if (index != -1) {
            people[index] = personSaved!!
            notifyItemChanged(index)
        }
    }

    fun insertItem(personSaved: Person?) {
        people[1] = personSaved!!
        notifyItemInserted(1)
    }

    fun deleteItem(person: Person) {
        val index = people.indexOfFirst { it.id == person.id }
        if (index != -1) {
            people.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    class ViewHolder(private val binding: PersonItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, listener: PersonClickListener?) {
            binding.lblContactName.text = item.name + " " + item.lastName
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnContactOpenDetail.setOnClickListener {
                listener?.onPersonDetailClick(item)
            }
            binding.btnDeleteItem.setOnClickListener {
                listener?.onPersonDeleteClick(item)
            }
        }
    }

    interface PersonClickListener {
        fun onPersonClick(person: Person)
        fun onPersonDetailClick(person: Person)
        fun onPersonDeleteClick(person: Person)
    }
}