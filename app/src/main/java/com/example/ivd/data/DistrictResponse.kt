package com.example.ivd.data

data class DistrictResponse(
    val status: Boolean,
    val message: String,
    val data: List<ZoneData>
)

data class ZoneData(
    val id: Int,
    val name: String,
    val zone: String,
    val status: Int,
    val created_by: Int,
    val updated_by: Int?,   // nullable because it can be null
    val created_at: String,
    val updated_at: String
)
