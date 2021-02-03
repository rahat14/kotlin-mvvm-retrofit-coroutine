package com.example.kotlin_mvvm_retrofit_coroutine.network.api


import com.example.kotlin_mvvm_retrofit_coroutine.model.ItemAlbum
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): NetworkResponse<List<ItemAlbum> , ItemAlbum>

}