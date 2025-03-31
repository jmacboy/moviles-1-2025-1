package com.example.practicacomponentes.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.practicacomponentes.databinding.NumberPickerLayoutBinding

class NumberPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding: NumberPickerLayoutBinding
    var value: Int = 0

    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NumberPickerLayoutBinding.inflate(
            inflater,
            this,
            true
        )
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnPlus.setOnClickListener { addValue() }
        binding.btnMinus.setOnClickListener { removeValue() }
    }

    private fun removeValue() {
        value--
        reloadScreen()
    }

    private fun addValue() {
        value++
        reloadScreen()
    }

    private fun reloadScreen() {
        binding.lblValue.text = value.toString()
    }
}