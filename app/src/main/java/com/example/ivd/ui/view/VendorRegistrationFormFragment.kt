package com.example.ivd.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.R
import com.example.ivd.adapter.DistrictAdapter
import com.example.ivd.adapter.ZoneAdapter
import com.example.ivd.data.DistrictRequest
import com.example.ivd.databinding.VendorRegistrationFormBinding
import com.example.ivd.repository.DistrictRepository
import com.example.ivd.viewmodel.DistrictViewModel
import com.example.ivd.viewmodelfactory.DistrictViewModelFactory

class VendorRegistrationFormFragment : Fragment() {
    private lateinit var _binding: VendorRegistrationFormBinding
    private val binding get() = _binding // Non-nullable backing property
    private lateinit var districtViewModel: DistrictViewModel

    val items = listOf("East Zone", "West Zone", "North Zone", "South Zone")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VendorRegistrationFormBinding.inflate(inflater, container, false)
        return binding.root

        /* val adapter = ArrayAdapter(
             requireContext(),
             android.R.layout.simple_spinner_item, items
         )

         binding.spinnerZone.adapter = adapter

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
         }*/

        /*  initViewModel()
          setupObservers()*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setupObservers()

        val adapter = ZoneAdapter(requireContext(), items)

        binding.spinnerZone.adapter = adapter

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
    }

    override fun onResume() {
        super.onResume()
        (activity as? HomeActivity)?.findViewById<TextView>(R.id.text_toolbar_title)?.text =
            "Vendor Registration"
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
