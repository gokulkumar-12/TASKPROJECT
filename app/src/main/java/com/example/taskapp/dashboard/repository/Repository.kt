package com.example.taskapp.dashboard.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskapp.dashboard.model.ItemDatas
import com.example.taskapp.dashboard.model.ItemDatasList
import com.example.taskapp.retrofit.RetrofitClient
import com.example.taskapp.utils.ApiResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    private var itemDataList: List<ItemDatasList>? = ArrayList<ItemDatasList>();

    val _apiResponse = MutableLiveData<ApiResult<ItemDatas>>()

    fun fetchData(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): MutableLiveData<ApiResult<ItemDatas>> {
        _apiResponse.value = ApiResult.Loading
        RetrofitClient.apiService.getItemData(q, from, sortBy, apiKey)
            .enqueue(object : Callback<ItemDatas> {
                override fun onResponse(call: Call<ItemDatas>, response: Response<ItemDatas>) {
                    if (response.isSuccessful) {
                        _apiResponse.value = ApiResult.Success(response.body()!!)
                    } else {
                        _apiResponse.value = ApiResult.Error(Throwable("API error"))
                    }
                }

                override fun onFailure(call: Call<ItemDatas>, t: Throwable) {
                    _apiResponse.value = ApiResult.Error(t)
                }
            })
        return _apiResponse
    }

    fun makeApiForSearchItem(s: String): MutableLiveData<ItemDatas?> {
        val data = MutableLiveData<ItemDatas?>()
        RetrofitClient.apiService.getSearchItems(s).enqueue(object :
            Callback<ItemDatas> {
            override fun onResponse(
                call: Call<ItemDatas>,
                response: Response<ItemDatas>
            ) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        val respose = response.body()
                        Log.d("response", " ${response.toString()}")
                        itemDataList = respose?.articles
                        data.postValue(respose)
                    }
                } else {
                    Log.e(
                        "TAG",
                        "onResponse: failure calling itemMaster ${response.code()}+${
                            response.errorBody().toString()
                        }"
                    )
                }
            }

            override fun onFailure(call: Call<ItemDatas>, t: Throwable) {
                data.postValue(null)
                Log.e("TAG", "onFailure: error on getting  List Items...$t")
            }

        })
        return data;
    }
}