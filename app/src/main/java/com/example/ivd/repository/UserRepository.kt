package com.example.ivd.repository

import com.example.ivd.data.UserRegistrationRequest
import com.example.ivd.data.UserRegistrationResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class UserRepository {
    suspend fun registerUser(request: UserRegistrationRequest): Response<UserRegistrationResponse> {
        return RetrofitClient.api.registerUser(request)
    }

}