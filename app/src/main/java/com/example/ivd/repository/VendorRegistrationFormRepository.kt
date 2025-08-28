package com.example.ivd.repository

import com.example.ivd.data.VendorDetailRegistrationFormRequest
import com.example.ivd.data.VendorRegisterFormResponse
import com.example.ivd.network.RetrofitClient
import retrofit2.Response

class VendorRegistrationFormRepository {
    suspend fun vendorForm(request: VendorDetailRegistrationFormRequest): Response<VendorRegisterFormResponse> {
        return RetrofitClient.api.vendorDetailForm(request)
    }
}