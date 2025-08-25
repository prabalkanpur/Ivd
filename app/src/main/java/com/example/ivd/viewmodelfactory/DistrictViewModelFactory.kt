package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.DistrictRepository
import com.example.ivd.viewmodel.DistrictViewModel

class DistrictViewModelFactory(private val repository: DistrictRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DistrictViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DistrictViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}