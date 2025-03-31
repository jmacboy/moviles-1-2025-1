package com.example.practicacomponentes.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.practicacomponentes.databinding.NumberPickerLayoutBinding
import androidx.core.content.withStyledAttributes

class NumberPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding: NumberPickerLayoutBinding
    var value: Int = 0
        set(value){
            field = value
            reloadScreen()
            onValueChange?.invoke(value)
        }
    private var onValueChange: ((Int) -> Unit)? = null

    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NumberPickerLayoutBinding.inflate(
            inflater,
            this,
            true
        )
        setupEventListeners()
        readXmlAttrs(attrs)
    }

    private fun readXmlAttrs(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        context.withStyledAttributes(
            attrs,
            com.example.practicacomponentes.R.styleable.NumberPicker
        ) {
            value =
                getInt(com.example.practicacomponentes.R.styleable.NumberPicker_initialNumber, 0)
            reloadScreen()
        }

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
    fun setOnValueChangeListener(listener: (Int) -> Unit) {
        onValueChange = listener
    }
}