package com.example.noname.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noname.DataClasses.FoodList
import com.example.noname.DataClasses.Meal
import com.example.noname.Retrofit.FoodApiService
import com.example.noname.Retrofit.RetrofitInstance
import com.example.noname.activities.FoodActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel(): ViewModel() {
private var foodDetailsLiveData = MutableLiveData<Meal>()

//Añadimos una nueva clase para que se vean los detalles de cada comida
    fun getFoodDetail(id:String){
        RetrofitInstance.api.GetFoodDetails(id).enqueue(object : Callback<FoodList>{
            override fun onResponse(call: Call<FoodList>, response : Response<FoodList>) {
               if (response.body()!= null){
                   foodDetailsLiveData.value = response.body()!!.meals[0]
               }
                else
                    return
            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
               Log.d("FoodActivity", t.message.toString())
            }
        })

    }

    fun observeFoodDetailLiveData():LiveData<Meal>{
        return foodDetailsLiveData
    }

}