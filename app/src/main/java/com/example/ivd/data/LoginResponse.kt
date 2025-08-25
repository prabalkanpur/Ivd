package com.example.ivd.data

data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val user: User,
    val status: Boolean
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String?, // nullable
    val photo: String?,             // nullable
    val phone: Long,
    val status: Int,
    val created_by: String?,        // nullable
    val updated_by: String?,        // nullable
    val created_at: String,
    val updated_at: String,
    val plan_id: String?,           // nullable
    val country_id: String?,        // nullable
    val address: String?              // nullable
)
