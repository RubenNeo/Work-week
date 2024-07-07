package com.example.noname.ViewModel

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.noname.DataClasses.FoodList
import com.example.noname.DataClasses.Meal
import com.example.noname.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    private var  randomFoodLiveData = MutableLiveData<Meal>()

    fun getRandomFood() {

        RetrofitInstance.api.GetRandomFood().enqueue(object : Callback<FoodList> {
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if (response.body() != null) {
                    val RandomFood: Meal = response.body()!!.meals[0]
                    randomFoodLiveData.value = RandomFood

                } else {
                    return
                }
            }

            override fun onFailure(p0: Call<FoodList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomFoodLiveData():LiveData<Meal>{
    return randomFoodLiveData

    }
}