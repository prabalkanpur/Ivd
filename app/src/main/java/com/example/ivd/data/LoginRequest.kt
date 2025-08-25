package com.example.ivd.data

data class LoginRequest(
    val email: String,
    val password: String
) {

    override fun toString(): String {
        return "LoginRequest(email='$email', password='$password')"
    }
}
