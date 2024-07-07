package com.example.noname.ListFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.noname.DataClasses.FoodList
import com.example.noname.DataClasses.Meal
import com.example.noname.R
import com.example.noname.Retrofit.RetrofitInstance
import com.example.noname.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random
import com.example.noname.DataClasses.Meal as Meal1


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitInstance.api.GetRandomFood().enqueue(object : Callback<FoodList>{
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if (response.body() != null){
                    val RandomFood : Meal = response.body()!!.meals[0]
                    Glide.with(this@HomeFragment)
                        .load(RandomFood.strMealThumb)
                        .into(binding.randomFood)
                }else{
                    return
                }
            }

            override fun onFailure(p0: Call<FoodList>, t: Throwable) {
             Log.d("HomeFragment",t.message.toString() )
            }

        })


    }


}