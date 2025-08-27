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
import com.example.ivd.adapter.CategoryAdapter
import com.example.ivd.adapter.DistrictAdapter
import com.example.ivd.adapter.SubCategoryAdapter
import com.example.ivd.adapter.ZoneAdapter
import com.example.ivd.data.DistrictRequest
import com.example.ivd.data.SubCategoryRequest
import com.example.ivd.databinding.VendorRegistrationFormBinding
import com.example.ivd.repository.CategoryRepository
import com.example.ivd.repository.DistrictRepository
import com.example.ivd.repository.SubCategoryRepository
import com.example.ivd.viewmodel.CategoryViewModel
import com.example.ivd.viewmodel.DistrictViewModel
import com.example.ivd.viewmodel.SubCategoryViewModel
import com.example.ivd.viewmodelfactory.CategoryViewModelFactory
import com.example.ivd.viewmodelfactory.DistrictViewModelFactory
import com.example.ivd.viewmodelfactory.SubcategoryViewModelFactory

class VendorRegistrationFormFragment : Fragment() {
    private lateinit var _binding: VendorRegistrationFormBinding
    private val binding get() = _binding // Non-nullable backing property
    private lateinit var districtViewModel: DistrictViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var subCategoryViewModel: SubCategoryViewModel

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
        fetchCategory()
        setupCategoryObservers()
        setupSubcategoryObservers()

        binding.recycleSubCategory.layoutManager = LinearLayoutManager(activity)

        val adapter = ZoneAdapter(requireContext(), items)

        binding.spinnerZone.adapter = adapter

        binding.spinnerZone.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val selectedZone = binding.spinnerZone.selectedItem.toString()
                fetchDistrict(selectedZone)

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

        val categoryRepository = CategoryRepository()
        val categoryVmFactory = CategoryViewModelFactory(categoryRepository)
        categoryViewModel =
            ViewModelProvider(this, categoryVmFactory)[CategoryViewModel::class.java]

        val subCategoryRepository = SubCategoryRepository()
        val subCategoryVmFactory = SubcategoryViewModelFactory(subCategoryRepository)
        subCategoryViewModel =
            ViewModelProvider(this, subCategoryVmFactory)[SubCategoryViewModel::class.java]
    }

    private fun fetchDistrict(zone: String) {
        val request = DistrictRequest(
            zone = zone,
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

    private fun fetchCategory() {
        categoryViewModel.getCategoryRequest()
        //binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupCategoryObservers() {
        categoryViewModel.categoryResponse.observe(activity) { response ->
            //binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == "success") {
                        val categoryData = response.data

                        // Extract only names
                        val names = categoryData.map { it.name }

                        val adapter = CategoryAdapter(requireContext(), names)
                        binding.spinnerCategory.adapter = adapter

                        binding.spinnerZone.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>,
                                view: View, position: Int, id: Long
                            ) {
                                //  val selectedZone = binding.spinnerZone.selectedItem.toString()
                                val categoryid = categoryData[0].parent_id
                                fetchSubcategory(categoryid)

                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {
                                // write code to perform some action
                            }
                        }

                    } else {
                        Log.d("VendorRegistrationFragment", "No district found")
                    }
                }
            }
        }
    }

    private fun fetchSubcategory(categoryId: Int) {
        val request = SubCategoryRequest(
            parent_id = categoryId,
        )
        subCategoryViewModel.getDistrictRequest(request)
        //binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupSubcategoryObservers() {
        subCategoryViewModel.subCategoryResponse.observe(activity) { response ->
            //binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == "success") {
                        /* val subcategory = response.data.get(0).children

                         // Extract only names
                         val names = subcategory.map { it.name }

                         val adapter = DistrictAdapter(requireContext(), names)
                         binding.spinnerDistrict.adapter = adapter*/
                        val body = response.data
                        body?.let {
                            // loop through all parent categories
                            for (category in it) {
                                Log.d(
                                    "API",
                                    "Parent: ${category.name}, parent_id: ${category.parent_id}"
                                )
                                // check if children exist
                                category.children?.let { childrenList ->
                                    val adapter = SubCategoryAdapter(requireContext(), childrenList)
                                    binding.recycleSubCategory.adapter = adapter
                                    for (child in childrenList) {
                                        val childName = child.name
                                        Log.d(
                                            "API",
                                            "   Child: ${child.name}, parent_id: ${child.parent_id}"
                                        )

                                    }
                                }
                            }
                        }

                    } else {
                        Log.d("VendorRegistrationFragment", "No district found")
                    }
                }
            }
        }
    }
}
