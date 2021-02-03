package com.example.kotlin_mvvm_retrofit_coroutine.repository

import com.example.kotlin_mvvm_retrofit_coroutine.model.ItemAlbum
import com.example.kotlin_mvvm_retrofit_coroutine.network.MyRetrofitBuilder
import com.example.kotlin_mvvm_retrofit_coroutine.utils.Resource
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository() {

//    val showProgress = MutableLiveData<Boolean>()
//
//    // val todoList = MutableLiveData<List<todoItem>>()
//    val errorMsg = MutableLiveData<String>()
//
//    fun changeState() {
//        showProgress.value = !(showProgress.value != null && showProgress.value!!)
//    }
//
//    fun getError() {
//
//
//    }

//
//    fun loadToxdos() {
//        showProgress.value = true
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//
//                val response = MyRetrofitBuilder.apiService.getTodo()
//
//                withContext(Main){
//                    when (response) {
//                        is NetworkResponse.Success -> {
//                            // Handle successful response
//                            todoList.value = response.body
//                            errorMsg.value = "Loaded  ${response.code}"
//                        }
//                        is NetworkResponse.ServerError -> {
//                            // Handle server error
//                            errorMsg.value = "Error With Response Code : Server ERror ${response.code}"
//                        }
//                        is NetworkResponse.NetworkError -> {
//                            // Handle network error
//                            errorMsg.value = "Error With Response Code : NetworkError Error "
//                        }
//                        is NetworkResponse.UnknownError -> {
//                            // Handle other errors
//                            errorMsg.value = "Error With Response Code : UnknownError Error "
//                        }
//                    }
//                }
//
//
//            } catch (e: Exception) {
//                withContext(Main) {
//                    errorMsg.value = "Error With Response Code : ${e.message}"
//                    showProgress.value = false
//                }
//            }
//        }
//    }

    // flow way
    fun loadTodos() : Flow<Resource<List<ItemAlbum>>> = flow {
        val responses = MyRetrofitBuilder.apiService.getPhotos()
        emit(Resource.loading(data = null))

        when (responses) {
            is NetworkResponse.Success -> {
                // Handle successful response
                // todoList.value = response.body
                emit(Resource.success(data = responses.body ))
            }
            is NetworkResponse.ServerError -> {
                emit(
                    Resource.error(
                        data = null,
                        message = "Error With Response Code : Server ERror ${responses.code}"
                    )
                )
            }
            is NetworkResponse.NetworkError -> {
                emit(
                    Resource.error(
                        data = null,
                        message = "Error With Response Code : Network Error"
                    )
                )
            }

            is NetworkResponse.UnknownError -> {
                emit(
                    Resource.error(
                        data = null,
                        message = "Error With Response Code : Server ERror ${responses.code}"
                    )
                )
            }



        }


    }.flowOn(Dispatchers.IO)
}






