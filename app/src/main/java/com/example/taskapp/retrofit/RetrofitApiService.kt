package com.example.taskapp.retrofit

import com.example.taskapp.dashboard.model.ItemDatas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    @GET("everything")
    fun getItemData(@Query("q") q:String,@Query("from") from:String,@Query("sortBy") sortBy:String, @Query("apiKey") apiKey:String):Call<ItemDatas>

    @GET("articles/")
    fun getSearchItems(@Query("title_contains") s:String): Call<ItemDatas>


}