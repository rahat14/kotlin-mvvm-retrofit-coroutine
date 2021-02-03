package com.example.kotlin_mvvm_retrofit_coroutine.ui

import android.app.Application
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_mvvm_retrofit_coroutine.adapter.MainListAdapter
import com.example.kotlin_mvvm_retrofit_coroutine.databinding.ActivityMainBinding
import com.example.kotlin_mvvm_retrofit_coroutine.model.ItemAlbum
import com.example.kotlin_mvvm_retrofit_coroutine.utils.Status
import com.example.kotlin_mvvm_retrofit_coroutine.viewmodels.MainViewModel


private lateinit var mainViewModel: MainViewModel


private lateinit var binding: ActivityMainBinding
private lateinit var mainListAdapter: MainListAdapter

class MainActivity : AppCompatActivity(), MainListAdapter.Interaction {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViews()
        setupViewModel()
        setupObservers()

        // now load the list
        mainViewModel.getAllTodos();
    }

    private fun setupViews() {
        // init rev
        binding.rcvList2.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            mainListAdapter = MainListAdapter(this@MainActivity)
            adapter = mainListAdapter
        }


    }

    private fun setupObservers() {
        mainViewModel.todoList.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        // submit the list
                        binding.pbar.visibility = View.GONE
                        mainListAdapter.submitList(resource.data!!)
                        for (item in resource.data) {
                            Log.d("TAG", "setupObservers:  ${item.id} :  ${item.title}")
                        }
                    }
                    Status.ERROR -> {
                        binding.pbar.visibility = View.GONE
                        Toast.makeText(this , "Error : ${resource.message}" , Toast.LENGTH_LONG).show()
                        Log.d("TAG", "setupObservers: " + resource.message)
                    }
                    Status.LOADING -> {
                        binding.pbar.visibility = View.VISIBLE
                    }
                }

            }


        })

//        mainViewModel.errorMsg.observe(this, {
//            Toast.makeText(applicationContext, "Error :  $it", Toast.LENGTH_LONG).show()
//        })

    }

    private fun setupViewModel() {

        mainViewModel = ViewModelProvider(
            this
        ).get(
            MainViewModel::class.java
        )


    }

    override fun onItemSelected(position: Int, item: ItemAlbum) {
        Log.d("TAG", "onItemClicked:  position :  $position  Title : ${item.title}")
    }
}