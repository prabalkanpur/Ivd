package com.example.ivd.data

data class VendorDetailResponse(
    val status: Boolean,
    val message: String,
    val vendor: VendorProfileData
)

data class VendorProfileData(
    val id: Int,
    val name: String,
    val email: String,
    val phone: Long,
    val gst_no: String,
    val category: Int,
    val sub_category: String,
    val zone: String,
    val district: Int,
    val company: String,
    val address: String,
    val status: Int,
    val created_at: String
)
