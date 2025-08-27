package com.example.ivd.data

data class VendorRegistrationResponse(
    val access_token: String,
    val token_type: String,
    val vendor: Vendor
)

data class Vendor(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val status: Int,
    val created_at: String,
    val updated_at: String
)
