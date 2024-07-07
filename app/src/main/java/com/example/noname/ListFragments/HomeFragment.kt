package com.example.noname.ListFragments


import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.noname.DataClasses.Meal


import com.example.noname.ViewModel.HomeViewModel
import com.example.noname.activities.Food_Activity
import com.example.noname.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel

    //otra variable para llamarla en la imagen 3
    private lateinit var randomMeal: Meal

    //hacemos companion objet de las zonas de cada descripcion de la tarea 1
    companion object {
        const val MEAL_ID = "com.example.noname.ListFragments.idMeal"
        const val MEAL_NAME = "com.example.noname.ListFragments.NameMeal"
        const val MEAL_THUMB = "com.example.noname.ListFragments.thumbMeal"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this).get(HomeViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomFood()
        observerRandomFood()
        onRandomMealClick()


    }
//Añadimos cada campo del companion objet 4
    private fun onRandomMealClick() {
        binding.randomFood.setOnClickListener {
            val intent = Intent(activity, Food_Activity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }
//mejoramos el enlace 2
    private fun observerRandomFood() {
        homeMvvm.observeRandomFoodLiveData().observe(viewLifecycleOwner,
            { meal ->
                Glide.with(this@HomeFragment)
                    .load(meal!!.strMealThumb)
                    .into(binding.randomFood)

                this.randomMeal = meal


            })
    }
}

