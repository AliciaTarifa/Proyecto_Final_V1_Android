package com.curso.android.app.practica.proyectofinalv1.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.curso.android.app.practica.proyectofinalv1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.textCompare.observe(this) {
            println("Recibimos un nuevo texto. $it")
            //binding.editText1.setText(it.text1)
            //binding.editText2.setText(it.text2)
            binding.resultText.text = "${it.result}"
        }

        binding.editText1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                binding.buttonCompare.isEnabled = true
            }
        })

        binding.editText2.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                binding.buttonCompare.isEnabled = true
            }
        })

        binding.buttonCompare.setOnClickListener {
            println("binding.buttonCompare.setOnClickListener. ${binding.editText1.text} ${binding.editText2.text}")
            mainViewModel.compareInputs("${binding.editText1.text}", "${binding.editText2.text}")
        }
    }
}