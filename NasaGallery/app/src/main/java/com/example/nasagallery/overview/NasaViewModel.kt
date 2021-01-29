package com.example.nasagallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasagallery.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NasaViewModel() : ViewModel() {

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>>
        get() = _items

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun getData(searchValue: String) {

        RetrofitApi.retrofitService.getData(searchValue).enqueue(object : Callback<CollectionNasa> {

            override fun onResponse(
                call: Call<CollectionNasa>,
                response: Response<CollectionNasa>
            ) {
                _items.value = response.body()?.collection?.items
            }

            override fun onFailure(call: Call<CollectionNasa>, t: Throwable) {
                _error.value = "Failure: " + t.message
            }

        })
    }
}