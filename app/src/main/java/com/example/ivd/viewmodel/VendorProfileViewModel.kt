package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.data.VendorDetailResponse
import com.example.ivd.repository.AuthRepository
import com.example.ivd.repository.VendorProfileRepository
import kotlinx.coroutines.launch

class VendorProfileViewModel (private val repository: VendorProfileRepository) : ViewModel() {
    private val _vendorProfileResponse = MutableLiveData<VendorDetailResponse>()
    val vendorProfileResponse: LiveData<VendorDetailResponse> get() = _vendorProfileResponse

    fun vendorProfileData(id: Int) {
        Log.d("request",id.toString())
        viewModelScope.launch {
            try {
                val response = repository.getVendorProfile(id)
                if (response.isSuccessful) {
                    _vendorProfileResponse.postValue(response.body())
                } else {
                    Log.d("LoginViewModel","LoginViewModel failed")
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel",e.printStackTrace().toString())
            }
        }
    }
}