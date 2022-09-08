package com.example.shoppingappproject.view.Other

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.shoppingappproject.databinding.ActivityRegistrationBinding
import com.example.shoppingappproject.model.remote.VolleyHandler
import com.example.shoppingappproject.model.remote.data.user.User
import com.example.shoppingappproject.presenter.registration.RegistrationMVP
import com.example.shoppingappproject.presenter.registration.RegistrationPresenter

class RegistrationActivity : AppCompatActivity(), RegistrationMVP.RegistrationView {
    private lateinit var binding:ActivityRegistrationBinding
    private lateinit var presenter: RegistrationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpEvent()
    }

    private fun setUpEvent() {
        presenter = RegistrationPresenter(VolleyHandler(this), this)
        binding.apply {
            val name = edtName.text.toString()
            val mobile = edtMobile.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            btnRegister.setOnClickListener{
                presenter.registerUser(
                    User(name,mobile,email,password)
                )
                presenter.registerUserAnalytics(
                    User(name,mobile,email,password)
                )
            }
        }
    }

    override fun setResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad(isLoading: Boolean) {
        if(isLoading){
            binding.circularProgress.visibility = View.VISIBLE
        }else{
            binding.circularProgress.visibility = View.GONE
        }
    }
}