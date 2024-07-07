package com.example.noname.Retrofit

import com.example.noname.DataClasses.FoodList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {


    @GET ("random.php")
    fun GetRandomFood():Call<FoodList>
//añadimos la llamada para que se vea los detalles o como se hace cada receta 11
    @GET ("lookup.php?")
    fun GetFoodDetails(@Query("i") id:String) : Call<FoodList>
}