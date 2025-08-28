package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.VendorRegistrationFormRepository
import com.example.ivd.viewmodel.VendorRegistrationFormViewModel

class VendorRegistrationFormViewModelFactory(private val repository: VendorRegistrationFormRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VendorRegistrationFormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VendorRegistrationFormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}