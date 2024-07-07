package com.example.noname.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.noname.ListFragments.HomeFragment
import com.example.noname.R
import com.example.noname.databinding.ActivityFoodBinding
//Metemos el binding para actualizar el activity con sus respectivos atributos 5
class Food_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityFoodBinding
    private lateinit var mealId : String
    private lateinit var mealName : String
    private lateinit var mealThumb : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
//funcion para la informacion de la comida 6
        getFoodInformationFromIntent()
//En esta funcion usamos el Glide para esa imagen 7
        setInformationInViews()
    }
    //funcion de la imagen Glide
    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.FoodDetail)

    }

    // ahora si llamamos a cada una de las variables que hemos realizado arriba  7
    private fun getFoodInformationFromIntent() {
       val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }
}