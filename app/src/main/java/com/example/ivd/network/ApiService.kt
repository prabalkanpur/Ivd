package com.example.ivd.network


import com.example.ivd.data.CategoryResponse
import com.example.ivd.data.DistrictResponse
import com.example.ivd.data.LoginRequest
import com.example.ivd.data.LoginResponse
import com.example.ivd.data.SubCategoryResponse
import com.example.ivd.data.UserRegistrationRequest
import com.example.ivd.data.UserRegistrationResponse
import com.example.ivd.data.VendorDetailRegistrationFormRequest
import com.example.ivd.data.VendorDetailResponse
import com.example.ivd.data.VendorRegisterFormResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("register/user")
    suspend fun registerUser(@Body request: UserRegistrationRequest): Response<UserRegistrationResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    /*@GET("districts")
    suspend fun getDistrict(@Body request: DistrictRequest): Response<DistrictResponse>*/
    @GET("districts")
    suspend fun getDistrict(
        @Query("zone") stateId: String
    ): Response<DistrictResponse>

    @GET("categories")
    suspend fun getCategory(): Response<CategoryResponse>

    @GET("categories")
    suspend fun getSubcategory(
        @Query("parent_id") category_id: String
    ): Response<SubCategoryResponse>

    @POST("register/vendor")
    suspend fun vendorDetailForm(@Body request: VendorDetailRegistrationFormRequest): Response<VendorRegisterFormResponse>

    /*@GET("vendor")
    suspend fun vendorDetail(
        @Query("id") vendorId: String
    ): Response<VendorDetailResponse>*/

    @GET("vendor/{id}")
    suspend fun vendorDetail(
        @Path("id") id: Int
    ): Response<VendorDetailResponse>
}