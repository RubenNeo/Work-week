package com.example.noname.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.noname.DataClasses.Meal
import com.example.noname.ListFragments.HomeFragment
import com.example.noname.R
import com.example.noname.ViewModel.FoodViewModel
import com.example.noname.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink : String
    private lateinit var mealMvvm: FoodViewModel//añadimos foodViewModel a este activity mediante una variable 13

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Llamar a esta función antes de cualquier operación que dependa de las variables
        mealMvvm = ViewModelProvider(this).get(FoodViewModel::class.java)

        getFoodInformationFromIntent()

        setInformationInViews()

        loadingCase()

        mealMvvm.getFoodDetail(mealId)
        observerMealDetailsLiveData()

       onYoutubeImageClick()

    }
//Añadimos el boton de Youtube
    private fun onYoutubeImageClick() {
       binding.imgYoutube.setOnClickListener {
        val intent = Intent (Intent.ACTION_VIEW, Uri.parse(youtubeLink))
           startActivity(intent)
       }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observeFoodDetailLiveData().observe(this, object : Observer<Meal> {
            override fun onChanged(t: Meal) {
                onResponseCase()
                val meal = t

                binding.CategoryId.text = "Category : ${meal.strCategory}"
                binding.AreaId.text = "Area : ${meal.strArea}"
                binding.InstruccionsId.text = meal.strInstructions

                youtubeLink = meal.strYoutube

            }

        })
    }


    //funcion de la imagen Glide
    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.FoodDetail)

        //binding para que al clickar salga el mismo nombre
        binding.collapsing.title = mealName
        // Añadimos el color de la comida para que se vea mejor en la pantalla, al deslizar el collapse hacia arriba
        binding.collapsing.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        // este binding si es para cambiar el color al clickar en la comida
        binding.collapsing.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    // ahora si llamamos a cada una de las variables que hemos realizado arriba
    private fun getFoodInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFavorite.visibility = View.INVISIBLE
        binding.InstruccionsId.visibility = View.INVISIBLE
        binding.CategoryId.visibility = View.INVISIBLE
        binding.AreaId.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnFavorite.visibility = View.VISIBLE
        binding.InstruccionsId.visibility = View.VISIBLE
        binding.CategoryId.visibility = View.VISIBLE
        binding.AreaId.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE


    }


}
