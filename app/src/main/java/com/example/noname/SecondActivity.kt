package com.example.noname

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.noname.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar NavController con el NavHostFragment
        navController = Navigation.findNavController(this, R.id.total_fragment)

        // Configurar BottomNavigationView con NavController
        NavigationUI.setupWithNavController(binding.btnNavigation, navController)
    }
}
