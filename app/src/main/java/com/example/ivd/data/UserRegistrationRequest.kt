package com.example.ivd.data



data class UserRegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
) {
    override fun toString(): String {
        return "UserRegistrationRequest(name='$name', email='$email', password='$password', phone='$phone')"
    }
}