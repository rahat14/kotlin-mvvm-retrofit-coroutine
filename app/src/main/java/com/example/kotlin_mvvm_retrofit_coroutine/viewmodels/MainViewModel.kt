package com.example.kotlin_mvvm_retrofit_coroutine.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_mvvm_retrofit_coroutine.model.ItemAlbum
import com.example.kotlin_mvvm_retrofit_coroutine.repository.MainRepository
import com.example.kotlin_mvvm_retrofit_coroutine.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val repository = MainRepository()

    //   val showProgress: LiveData<Boolean>
    val todoList = MutableLiveData<Resource<List<ItemAlbum>>>()
//    val errorMsg: LiveData<String>

//    init {
//        this.showProgress = repository.showProgress
//        // this.todoList = repository.todoList
//        this.errorMsg = repository.errorMsg
//    }
//
//    fun changeState() {
//        repository.changeState()
//    }

    fun getAllTodos() {
        // way 1 :
        viewModelScope.launch {
            repository.loadTodos()
                .catch { e ->
                    Log.d("TAG", "getAllTodos: " + e.message)

                }.collect { postData ->
                    todoList.value = postData
                }
        }


    }

}