package com.example.ivd.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ivd.R
import com.example.ivd.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initView()
        val firstFragment = HomeFragment()
        loadFragment(firstFragment)
    }

    private fun loadFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    private fun initView() {
        binding.bottomNavigationIvd.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.nav_profile -> {
                    loadFragment(VendorListFragment())
                    true
                }

                R.id.nav_settings -> {
                    loadFragment(SettingFragment())
                    true
                }

                R.id.nav_ivd -> {
                    Log.d("BottomNav", "IVD Clicked")
                    loadFragment(VendorRegistrationFormFragment())
                    true
                }

                else -> false
            }
        }
    }
}
