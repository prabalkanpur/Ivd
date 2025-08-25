package com.example.ivd.repository

import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class AuthRepository {

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitClient.api.login(request)
    }
}