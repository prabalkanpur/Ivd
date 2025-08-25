package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import com.example.ivd.repository.DistrictRepository
import kotlinx.coroutines.launch

class DistrictViewModel(private val repository: DistrictRepository) : ViewModel() {
    private val _districtResponse = MutableLiveData<DistrictResponse>()
    val districtResponse: LiveData<DistrictResponse> get() = _districtResponse

    fun getDistrictRequest(request: DistrictRequest) {
        Log.d("request", request.toString())
        viewModelScope.launch {
            try {
                val response = repository.district(request)
                if (response.isSuccessful) {
                    _districtResponse.postValue(response.body())
                } else {
                    Log.d("LoginViewModel", "LoginViewModel failed")
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel", e.printStackTrace().toString())
            }
        }
    }
}