package com.example.ivd.data


data class UserRegistrationResponse(
    val message: String,
    val status: Boolean,
    val data: UserData
)

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val status: Int,
    val created_at: String,
    val updated_at: String
)