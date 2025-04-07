package com.example.practicalista.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalista.databinding.PersonItemLayoutBinding
import com.example.practicalista.models.Person

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

    class ViewHolder(private val binding: PersonItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, listener: PersonClickListener?) {
            binding.lblContactName.text = item.name + " " + item.lastName
            binding.lblContactPhone.text = item.phone
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnContactOpenDetail.setOnClickListener {
                listener?.onPersonDetailClick(item)
            }
        }
    }

    interface PersonClickListener {
        fun onPersonClick(person: Person)
        fun onPersonDetailClick(person: Person)
    }
}