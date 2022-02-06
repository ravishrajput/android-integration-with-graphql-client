package com.ravish.android.integration.with.graphql.client

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeLivedata()
        mainViewModel.getAllFlights()
    }

    private fun observeLivedata() {
        mainViewModel.allFlightLiveData.observe(this, {
            Log.i("GraphQLData", "observeLivedata: $it")
        })
    }
}