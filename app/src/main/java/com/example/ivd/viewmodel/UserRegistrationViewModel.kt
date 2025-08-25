package com.example.ivd.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.UserRegistrationRequest
import com.example.ivd.data.UserRegistrationResponse
import com.example.ivd.repository.UserRepository
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val repository: UserRepository) : ViewModel() {


    private val _registerResponse = MutableLiveData<UserRegistrationResponse>()
    val registerResponse: LiveData<UserRegistrationResponse> get() = _registerResponse

    fun registerUser(request: UserRegistrationRequest) {
        viewModelScope.launch {
            try {
                val response = repository.registerUser(request)
                if (response.isSuccessful) {
                    _registerResponse.postValue(response.body())
                } else {
                    /*  _registerResponse.postValue(
                          UserRegistrationResponse(
                              "Registration failed", "false",
                              data = TODO()
                          )
                      )*/
                    Log.d("UserViewModel", "Registration failed")
                }
            } catch (e: Exception) {
                /*_registerResponse.postValue(
                    UserRegistrationResponse(
                        "Error: ${e.localizedMessage}", "false",
                        data = TODO()
                    )
                )*/
                Log.d("UserViewModel", e.printStackTrace().toString())
            }
        }
    }
}