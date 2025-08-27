package com.example.ivd.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ivd.adapter.CategoryAdapter
import com.example.ivd.adapter.SubCategoryAdapter
import com.example.ivd.adapter.ZoneAdapter
import com.example.ivd.databinding.CaregoryAddFragmentBinding
import com.example.ivd.databinding.HomeFragmentBinding

class CategoryAddFragment : Fragment() {

    private lateinit var _binding: CaregoryAddFragmentBinding
    private val binding get() = _binding // Non-nullable backing property

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CaregoryAddFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val categoryAdapter = CategoryAdapter(requireContext(), zoneList)
        //binding.spinnerCategory.adapter = categoryAdapter

        //val sunCategoryAdapter = SubCategoryAdapter(requireContext(), zoneList)
        //binding.spinnerSubCategory.adapter = sunCategoryAdapter

        binding.btnSave.setOnClickListener {

        }

        binding.btnCancel.setOnClickListener {

        }
    }


    override fun onResume() {
        super.onResume()
    }
}