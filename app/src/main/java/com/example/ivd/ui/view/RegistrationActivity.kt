package com.example.ivd.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.R
import com.example.ivd.adapter.UserTypeAdapter
import com.example.ivd.adapter.ZoneAdapter
import com.example.ivd.data.UserRegistrationRequest
import com.example.ivd.databinding.ActivityRegistrationBinding
import com.example.ivd.repository.UserRepository
import com.example.ivd.viewmodel.UserRegistrationViewModel
import com.example.ivd.viewmodelfactory.UserViewModelFactory


class RegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: UserRegistrationViewModel
    private val userTypes = listOf("User", "Vendor")
    private var selectedUserType: String = userTypes[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        initView()
        initViewModel()
        setupObservers()

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun initView() {
        val adapter = UserTypeAdapter(this, userTypes)
        binding.spinnerUserType.adapter = adapter
        binding.spinnerUserType.onItemSelectedListener = this
    }

    private fun initViewModel() {
        val repository = UserRepository()
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UserRegistrationViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.registerResponse.observe(this) { response ->
            binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == true) {
                        Toast.makeText(
                            this,
                            response.message ?: "Registration successful",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            response.message ?: "Registration failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun registerUser() {
        val name = binding.userName.text.toString().trim()
        val email = binding.emailId.text.toString().trim()
        val password = binding.password.text.toString().trim()
        val phone = binding.mobileNo.text.toString().trim()

        when {
            name.isEmpty() -> binding.textInputUsername.error = "Username is required"
            email.isEmpty() -> binding.textInputEmail.error = "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> binding.textInputEmail.error = "Invalid email format"
            phone.isEmpty() -> binding.textInputMob.error = "Mobile number is required"
            phone.length != 10 -> binding.textInputMob.error = "Mobile number must be 10 digits"
            password.isEmpty() -> binding.textInputPassword.error = "Password is required"
            password.length < 6 -> binding.textInputPassword.error = "Password must be at least 6 characters"
            else -> {
                binding.textInputUsername.error = null
                binding.textInputEmail.error = null
                binding.textInputMob.error = null
                binding.textInputPassword.error = null

                val request = UserRegistrationRequest(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone,
                )
                viewModel.registerUser(request)
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedUserType = userTypes[position]
        Toast.makeText(this, "Selected: $selectedUserType", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedUserType = userTypes[0]
    }
}


