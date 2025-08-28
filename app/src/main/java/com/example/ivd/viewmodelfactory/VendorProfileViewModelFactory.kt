package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.AuthRepository
import com.example.ivd.repository.VendorProfileRepository
import com.example.ivd.viewmodel.LoginViewModel
import com.example.ivd.viewmodel.VendorProfileViewModel

class VendorProfileViewModelFactory (private val repository: VendorProfileRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VendorProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VendorProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}