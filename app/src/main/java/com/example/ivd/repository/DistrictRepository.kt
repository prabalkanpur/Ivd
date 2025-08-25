package com.example.ivd.repository

import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class DistrictRepository {
    suspend fun district(request: DistrictRequest): Response<DistrictResponse> {
        return RetrofitClient.api.getDistrict(request)
    }
}