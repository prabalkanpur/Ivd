package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.data.VendorDetailRegistrationFormRequest
import com.example.ivd.data.VendorRegisterFormResponse
import com.example.ivd.repository.AuthRepository
import com.example.ivd.repository.VendorRegistrationFormRepository
import kotlinx.coroutines.launch

class VendorRegistrationFormViewModel(private val repository: VendorRegistrationFormRepository) : ViewModel() {
    private val _vendorFormResponse = MutableLiveData<VendorRegisterFormResponse>()
    val vendorFormResponse: LiveData<VendorRegisterFormResponse> get() = _vendorFormResponse

    fun vendorFormData(request: VendorDetailRegistrationFormRequest) {
        Log.d("request",request.toString())
        viewModelScope.launch {
            try {
                val response = repository.vendorForm(request)
                if (response.isSuccessful) {
                    _vendorFormResponse.postValue(response.body())
                } else {
                    Log.d("LoginViewModel","LoginViewModel failed")
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel",e.printStackTrace().toString())
            }
        }
    }
}