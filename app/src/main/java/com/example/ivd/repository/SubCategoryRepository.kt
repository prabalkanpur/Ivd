package com.example.ivd.repository

import com.example.ivd.data.SubCategoryRequest
import com.example.ivd.data.SubCategoryResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class SubCategoryRepository {

    suspend fun subCategory(request: SubCategoryRequest): Response<SubCategoryResponse> {
        return RetrofitClient.api.getSubcategory(request.toString())
    }
}