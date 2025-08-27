package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.CategoryRepository
import com.example.ivd.repository.DistrictRepository
import com.example.ivd.viewmodel.CategoryViewModel
import com.example.ivd.viewmodel.DistrictViewModel

class CategoryViewModelFactory (private val repository: CategoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}