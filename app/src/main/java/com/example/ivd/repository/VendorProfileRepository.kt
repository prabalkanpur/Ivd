package com.example.ivd.repository

import com.example.ivd.data.CategoryResponse
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.VendorDetailResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class VendorProfileRepository {
    suspend fun getVendorProfile(request: Int): Response<VendorDetailResponse> {
        return RetrofitClient.api.vendorDetail(request)
    }
}