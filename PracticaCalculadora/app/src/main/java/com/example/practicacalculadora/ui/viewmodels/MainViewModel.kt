package com.example.practicacalculadora.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicacalculadora.models.OperationType

class MainViewModel : ViewModel() {
    private var _result: MutableLiveData<String> = MutableLiveData("")
    val result: LiveData<String> = _result
    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber: Int = 0

    fun addNumber(num: Int) {
        _result.value += num
    }

    fun clearEverything() {
        prevNumber = 0
        currentOperation = OperationType.NONE
        _result.value = ""
    }

    fun clearOne() {
        if (_result.value?.isEmpty() == true) {
            return
        }
        _result.value = _result.value?.dropLast(1)
    }

    fun calculateResult() {
        var secondNumber = 0
        if (_result.value?.isNotEmpty() == true) {
            secondNumber = _result.value!!.toInt()
        }
        val operationResult = when (currentOperation) {
            OperationType.ADDITION -> prevNumber + secondNumber
            OperationType.SUBTRACTION -> prevNumber - secondNumber
            OperationType.MULTIPLICATION -> prevNumber * secondNumber
            OperationType.DIVISION -> {
                if (secondNumber != 0) {
                    prevNumber / secondNumber
                } else {
//                    Toast.makeText(this, "Error, división por 0", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            OperationType.NONE -> return
        }
        _result.value = operationResult.toString()
        currentOperation = OperationType.NONE
        prevNumber = 0
    }

    fun startOperation(operation: OperationType) {
        currentOperation = operation
        prevNumber = _result.value?.toInt()!!
        _result.value = ""
    }


}