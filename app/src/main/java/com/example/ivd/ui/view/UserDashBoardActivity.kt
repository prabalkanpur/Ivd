package com.example.ivd.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ivd.R
import com.example.ivd.databinding.ActivityHomeBinding
import com.example.ivd.databinding.ActivityUserDashBoardBinding

class UserDashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_dash_board)
        //setContentView(R.layout.activity_user_dash_board)
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        /*{ v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
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
