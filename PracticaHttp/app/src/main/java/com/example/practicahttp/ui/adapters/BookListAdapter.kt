package com.example.practicahttp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicahttp.databinding.BookListItemBinding
import com.example.practicahttp.models.Book
import com.example.practicahttp.models.BookList

class BookListAdapter(
    var items: BookList
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {
    private var onBookClickListener: OnBookClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    public fun setOnBookClickListener(listener: OnBookClick) {
        this.onBookClickListener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onBookClickListener)
    }

    fun setData(newData: BookList) {
        this.items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, listener: OnBookClick?) {
            binding.lblBookTitle.text = item.nombre
            Glide
                .with(binding.root.context)
                .load(item.imagen)
                .into(binding.imgBook)
            binding.lblBookAuthor.text = item.autor
            binding.root.setOnClickListener {
                listener?.onBookClick(item)
            }
        }
    }

    interface OnBookClick {
        fun onBookClick(post: Book)
    }
}