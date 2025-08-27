package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.CategoryResponse
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import com.example.ivd.repository.CategoryRepository
import com.example.ivd.repository.DistrictRepository
import kotlinx.coroutines.launch

class CategoryViewModel (private val repository: CategoryRepository) : ViewModel() {

    private val _categoryResponse = MutableLiveData<CategoryResponse>()
    val categoryResponse: LiveData<CategoryResponse> get() = _categoryResponse

    fun getCategoryRequest() {
        viewModelScope.launch {
            try {
                val response = repository.category()
                if (response.isSuccessful) {
                    _categoryResponse.postValue(response.body())
                } else {
                    Log.d("CategoryViewModel", "CategoryViewModel failed")
                }
            } catch (e: Exception) {
                Log.d("CategoryViewModel", e.printStackTrace().toString())
            }
        }
    }


}