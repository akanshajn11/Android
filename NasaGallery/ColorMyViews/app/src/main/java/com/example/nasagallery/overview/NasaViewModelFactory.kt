package com.example.nasagallery.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NasaViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NasaViewModel::class.java)) {
            return NasaViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}