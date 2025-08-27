package com.example.ivd.repository

import com.example.ivd.data.CategoryResponse
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class CategoryRepository {

    suspend fun category(): Response<CategoryResponse> {
        return RetrofitClient.api.getCategory()
    }
}