package com.example.ivd.data

data class VendorRegisterFormResponse(
    val access_token: String,
    val token_type: String,
    val vendor: VendorDetail,
    val status: Boolean
)

data class VendorDetail(
    val name: String,
    val email: String,
    val phone: String,
    val gst_no: String,
    val category: String,
    val sub_category: String, // comes as "2,3" (comma separated string)
    val zone: String,
    val district: String,
    val company: String,
    val address: String,
    val status: Int,
    val updated_at: String,
    val created_at: String,
    val id: Int
)