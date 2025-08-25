package com.example.ivd.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.repository.UserRepository
import com.example.ivd.viewmodel.UserRegistrationViewModel

class UserViewModelFactory (private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserRegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserRegistrationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}