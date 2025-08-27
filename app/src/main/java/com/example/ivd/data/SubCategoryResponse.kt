package com.example.ivd.data

data class SubCategoryResponse(
    val status: String,
    val message: String,
    val data: List<CategoryData>
)

data class CategoryData(
    val id: Int,
    val name: String,
    val parent_id: Int,
    val slug: String,
    val status: Int,
    val icon: String?,
    val image: String?,
    val banner: String?,
    val created_by: Int,
    val updated_by: Int?,
    val created_at: String,
    val updated_at: String,
    val attribute: String?,
    val childcategory: Boolean?,
    val children: List<SubCategory>?
)

data class SubCategory(
    val id: Int,
    val name: String,
    val parent_id: Int,
    val slug: String,
    val status: Int,
    val icon: String?,
    val image: String?,
    val banner: String?,
    val created_by: Int,
    val updated_by: Int?,
    val created_at: String,
    val updated_at: String,
    val attribute: String?
)
