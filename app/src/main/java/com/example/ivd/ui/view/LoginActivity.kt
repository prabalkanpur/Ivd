package com.example.ivd.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ivd.R
import com.example.ivd.data.LoginRequest
import com.example.ivd.databinding.ActivityLoginBinding
import com.example.ivd.repository.AuthRepository
import com.example.ivd.viewmodel.LoginViewModel
import com.example.ivd.viewmodelfactory.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initViewModel()
        setupObservers()

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        viewModel.loginResponse.observe(this) { response ->
            Log.d("prabal_login", response.toString())
            if (response != null) {
                if (response.user.status == 1){
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    Toast.makeText(this, "Welcome ${response.user.name}", Toast.LENGTH_SHORT).show()
                }else{
                    startActivity(Intent(this@LoginActivity, UserDashBoardActivity::class.java))
                    Toast.makeText(this, "Welcome ${response.user.name}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        val repository = AuthRepository()
        val factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun setupObservers() {
        viewModel.loginResponse.observe(this) { response ->
            //binding.progressBar.visibility = View.GONE
            when (response) {
                else -> {
                    if (response.status == true) {
                        Toast.makeText(
                            this,
                            "Login successful",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Login failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun loginUser() {
        val email = binding.loginMail.text.toString().trim()
        val password = binding.loginPassword.text.toString().trim()

        when {
            email.isEmpty() -> binding.textInputUsername.error = "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> binding.textInputUsername.error =
                "Invalid email format"

            password.isEmpty() -> binding.textInputPassword.error = "Password is required"
            password.length < 6 -> binding.textInputPassword.error =
                "Password must be at least 6 characters"

            else -> {
                binding.textInputUsername.error = null
                binding.textInputPassword.error = null

                val request = LoginRequest(
                    email = email,
                    password = password,
                )
                viewModel.login(request)
                //binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

}
