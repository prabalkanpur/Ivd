package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    fun login(request: LoginRequest) {
        Log.d("request",request.toString())
        viewModelScope.launch {
            try {
                val response = repository.login(request)
                if (response.isSuccessful) {
                    _loginResponse.postValue(response.body())
                } else {
                    Log.d("LoginViewModel","LoginViewModel failed")
                }
            } catch (e: Exception) {
               Log.d("LoginViewModel",e.printStackTrace().toString())
            }
        }
    }
}