package com.example.nasagallery.overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nasagallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel =
            ViewModelProvider(this, NasaViewModelFactory()).get(NasaViewModel::class.java)

        viewModel.getData("Mars")
        binding.lifecycleOwner = this


    }
}