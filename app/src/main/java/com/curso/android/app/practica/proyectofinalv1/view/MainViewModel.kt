package com.curso.android.app.practica.proyectofinalv1.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.practica.proyectofinalv1.model.TextCompare
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {
    val textCompare: LiveData<TextCompare> get() = _textCompare
    private var _textCompare = MutableLiveData<TextCompare>(TextCompare("", "",  ""))

    fun compareInputs(editText1: String, editText2: String,) {
        //val editText1 = _textCompare.value?.text1
        //val editText2 = _textCompare.value?.text2
        val result: String = if (editText1.isNullOrEmpty() && editText2.isNullOrEmpty()) {
            "Resultado: No se han ingresado datos para comparar"
        } else {
            if (editText1.equals(editText2)) {
                "Resultado: Los textos son iguales."
            } else {
                "Resultado: Los textos son diferentes."
            }
        }
        updateTextCompare(editText1!!, editText2!!, result)
    }
    fun updateTextCompare(text1: String, text2: String, result: String) {
        viewModelScope.launch {
            _textCompare.value = TextCompare(text1, text2, result)
        }
    }
}