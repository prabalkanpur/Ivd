package com.example.ivd.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ivd.R
import com.example.ivd.adapter.DistrictAdapter
import com.example.ivd.adapter.VendorListAdapter
import com.example.ivd.adapter.ZoneAdapter
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.VendorListModel
import com.example.ivd.databinding.HomeFragmentBinding
import com.example.ivd.repository.DistrictRepository
import com.example.ivd.viewmodel.DistrictViewModel
import com.example.ivd.viewmodelfactory.DistrictViewModelFactory

class HomeFragment : Fragment() {


    private lateinit var vendorList: ArrayList<VendorListModel>
    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding // Non-nullable backing property
    val zoneList = listOf("East Zone", "West Zone", "North Zone", "South Zone")
    private lateinit var districtViewModel: DistrictViewModel


    @Deprecated("Deprecated in Java")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerVendor.layoutManager = LinearLayoutManager(activity)

        val zoneAdapter = ZoneAdapter(requireContext(), zoneList)

        binding.spinnerZone.adapter = zoneAdapter

        binding.spinnerZone.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                fetchDistrict()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val adapter = VendorListAdapter(activity)
        binding.recyclerVendor.adapter = adapter

        initViewModel()
        setupObservers()
    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()

        (activity as? HomeActivity)?.findViewById<TextView>(R.id.text_toolbar_title)?.text =
            "Vendor List"
    }

    private fun initViewModel() {
        val repository = DistrictRepository()
        val factory = DistrictViewModelFactory(repository)
        districtViewModel = ViewModelProvider(this, factory)[DistrictViewModel::class.java]
    }

    private fun fetchDistrict() {
        val request = DistrictRequest(
            zone = "north",
        )
        districtViewModel.getDistrictRequest(request)
        //binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupObservers() {
        districtViewModel.districtResponse.observe(activity) { response ->
            //binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == true) {
                        val districtList = response.data

                        // Extract only names
                        val names = districtList.map { it.name }

                        val adapter = DistrictAdapter(requireContext(), names)
                        binding.spinnerDistrict.adapter = adapter

                    } else {
                        Log.d("VendorRegistrationFragment", "No district found")
                    }
                }
            }
        }
    }
}