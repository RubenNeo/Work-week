package com.example.noname.Retrofit

import com.example.noname.DataClasses.FoodList
import retrofit2.Call
import retrofit2.http.GET

interface FoodApiService {


    @GET ("random.php")
    fun GetRandomFood():Call<FoodList>
}