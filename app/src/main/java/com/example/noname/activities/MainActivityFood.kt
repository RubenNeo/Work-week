package com.example.noname.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.noname.databinding.ActivityFirstBinding

class MainActivityFood : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar el ProgressBar inicialmente
        binding.loader.visibility = View.VISIBLE

        // Ocultar el ProgressBar y mostrar el botón después de 3 segundos (3000 milisegundos)
        Handler().postDelayed({
            binding.loader.visibility = View.GONE
            binding.btnStart.visibility = View.VISIBLE
        }, 3000)

        binding.btnStart.setOnClickListener {
            // Aquí puedes iniciar la siguiente actividad o realizar otra acción
            val intent = Intent(this, SecondActivity::class.java )
            startActivity(intent)
        }
    }
}
