package com.example.taskapp.dashboard.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.dashboard.model.ItemDatas
import com.example.taskapp.dashboard.repository.Repository
import com.example.taskapp.retrofit.RetrofitClient
import com.example.taskapp.utils.ApiResult
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel(private val context: Context) : ViewModel() {

    private var repository: Repository? = null

    private val _apiResponse = MutableLiveData<ApiResult<ItemDatas>>()
    val apiResponse: LiveData<ApiResult<ItemDatas>> get() = _apiResponse


    fun init() {
        this.repository = Repository(context);
    }

   fun fetchData(q: String, from : String, sortBy : String, apiKey : String){
       _apiResponse.value = ApiResult.Loading
       viewModelScope.launch {
           repository?.fetchData(q,from,sortBy,apiKey)!!.observeForever(){
               _apiResponse.value = it
           }
       }
   }

    fun makeApiForSearchItem(s : String) : LiveData<ItemDatas?>?{
        return repository?.makeApiForSearchItem(s)
    }
}