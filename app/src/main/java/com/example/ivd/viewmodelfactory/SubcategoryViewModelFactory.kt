package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.SubCategoryRepository
import com.example.ivd.viewmodel.SubCategoryViewModel

class SubcategoryViewModelFactory (private val repository: SubCategoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubCategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}