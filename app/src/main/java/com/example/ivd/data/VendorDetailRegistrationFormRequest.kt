package com.example.ivd.data

data class VendorDetailRegistrationFormRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val company: String,
    val address: String,
    val gst_no: String,
    val category: String,
    val sub_category: String,  // comes as "2,3" (comma separated string)
    val zone: String,
    val district: String
)
