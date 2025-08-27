package com.example.ivd.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.DistrictResponse
import com.example.ivd.data.SubCategoryRequest
import com.example.ivd.data.SubCategoryResponse
import com.example.ivd.repository.SubCategoryRepository
import kotlinx.coroutines.launch

class SubCategoryViewModel(private val repository: SubCategoryRepository) : ViewModel() {

    private val _subCategoryResponse = MutableLiveData<SubCategoryResponse>()
    val subCategoryResponse: LiveData<SubCategoryResponse> get() = _subCategoryResponse

    fun getDistrictRequest(request: SubCategoryRequest) {
        Log.d("request", request.toString())
        viewModelScope.launch {
            try {
                val response = repository.subCategory(request)
                if (response.isSuccessful) {
                    _subCategoryResponse.postValue(response.body())
                } else {
                    Log.d("SubCategoryViewModel", "SubCategoryViewModel failed")
                }
            } catch (e: Exception) {
                Log.d("SubCategoryViewModel", e.printStackTrace().toString())
            }
        }
    }
}