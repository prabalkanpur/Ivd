package com.example.ivd.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ivd.R
import com.example.ivd.adapter.VendorListAdapter
import com.example.ivd.data.VendorListModel
import com.example.ivd.databinding.HomeFragmentBinding

class HomeFragment : Fragment(){


    private lateinit var vendorList: ArrayList<VendorListModel>
    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding // Non-nullable backing property


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
        // This will pass the ArrayList to our Adapter
        val adapter = VendorListAdapter(activity)
        binding.recyclerVendor.adapter = adapter
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
}