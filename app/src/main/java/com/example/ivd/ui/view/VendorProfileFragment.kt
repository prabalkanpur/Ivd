package com.example.ivd.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.R
import com.example.ivd.databinding.VendorProfileFragmentBinding
import com.example.ivd.preference.SharedPreferencesDataSource
import com.example.ivd.repository.VendorProfileRepository
import com.example.ivd.viewmodel.VendorProfileViewModel
import com.example.ivd.viewmodelfactory.VendorProfileViewModelFactory

class VendorProfileFragment : Fragment() {

    private lateinit var _binding: VendorProfileFragmentBinding
    private val binding get() = _binding // Non-nullable backing property
    private lateinit var vendorProfileViewModel: VendorProfileViewModel
    private lateinit var prefManager: SharedPreferencesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VendorProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefManager = SharedPreferencesDataSource(requireActivity())
        val id = prefManager.getId()
        initViewModel()
        id?.let {
            fetchCategory(id)
        }
        setupCategoryObservers()
    }

    private fun initViewModel() {
        val repository = VendorProfileRepository()
        val factory = VendorProfileViewModelFactory(repository)
        vendorProfileViewModel =
            ViewModelProvider(this, factory)[VendorProfileViewModel::class.java]
    }

    private fun fetchCategory(id: Int) {
        vendorProfileViewModel.vendorProfileData(id)
        //binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupCategoryObservers() {
        vendorProfileViewModel.vendorProfileResponse.observe(activity) { response ->
            //binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == true) {
                        val profiledata = response.vendor
                        binding.etVendorName.setText(profiledata.name.toString())
                        binding.etVendorEmail.setText(profiledata.email.toString())
                        binding.etCompanyAddress.setText(profiledata.address.toString())
                        binding.etMobileNo.setText(profiledata.phone.toString())
                        binding.gstNo.setText(profiledata.gst_no.toString())
                        Log.d("VendorProfileFragment", profiledata.toString())


                    } else {
                        Log.d("VendorProfileFragment", "No district found")
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? HomeActivity)?.findViewById<TextView>(R.id.text_toolbar_title)?.text =
            "Vendor Profile"
    }
}