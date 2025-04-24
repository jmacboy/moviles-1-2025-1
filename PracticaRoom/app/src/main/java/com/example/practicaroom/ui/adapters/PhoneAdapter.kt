package com.example.practicaroom.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.databinding.PhoneItemLayoutBinding
import com.example.practicaroom.db.models.Phone

class PhoneAdapter(
    private var phones: ArrayList<Phone>
) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {
    var phoneClickListener: PhoneClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhoneItemLayoutBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = phones[position]
        holder.bind(item, phoneClickListener)
    }

    fun setOnPhoneClickListener(listener: PhoneClickListener) {
        this.phoneClickListener = listener
    }

    fun setData(newPhones: java.util.ArrayList<Phone>?) {
        this.phones = newPhones!!
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: PhoneItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Phone, phoneClickListener: PhoneClickListener?) {
            binding.lblPhoneNumber.text = item.number
            binding.lblPhoneReference.text = item.reference
            binding.btnDeletePhone.setOnClickListener {
                phoneClickListener?.onPhoneDeleteClick(item)
            }
        }
    }

    interface PhoneClickListener {
        fun onPhoneDeleteClick(phone: Phone)
    }
}