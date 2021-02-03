package com.example.kotlin_mvvm_retrofit_coroutine.network

import com.example.kotlin_mvvm_retrofit_coroutine.network.api.ApiService
import com.example.kotlin_mvvm_retrofit_coroutine.utils.Constants
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitBuilder {


    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy {

        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}


