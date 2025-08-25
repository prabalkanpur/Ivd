package com.example.ivd.network


import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.data.UserRegistrationRequest
import com.example.ivd.data.UserRegistrationResponse
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST

interface ApiService {

    @POST("register/user")
    suspend fun registerUser(@Body request: UserRegistrationRequest): Response<UserRegistrationResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("districts")
    suspend fun getDistrict(@Body request: DistrictRequest): Response<DistrictResponse>
}