package com.example.shoppingappproject.view.Other

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingappproject.databinding.ActivityLoginBinding
import com.example.shoppingappproject.model.remote.Constants.USER_EMAIL
import com.example.shoppingappproject.model.remote.Constants.USER_MOBILE
import com.example.shoppingappproject.model.remote.Constants.USER_NAME
import com.example.shoppingappproject.model.remote.Constants.USER_PASSWORD
import com.example.shoppingappproject.model.remote.VolleyHandler
import com.example.shoppingappproject.model.remote.data.user.User
import com.example.shoppingappproject.presenter.login.LoginMVP
import com.example.shoppingappproject.presenter.login.LoginPresenter
import kotlin.math.log

class LoginActivity : AppCompatActivity() , LoginMVP.LoginView{
    private lateinit var binding:ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var presenter: LoginMVP.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(VolleyHandler(this), this)
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val user = User("", "", email, password)
            presenter.userLogin(user)
        }
        binding.createAccount.setOnClickListener {
            val intent: Intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setResult(message: String) {
    }

    override fun onLoad(isLoading: Boolean) {
    }

    override fun setLogin(user:User){
        val intent:Intent = Intent(this, MainActivity::class.java)
        sharedPreferences = getSharedPreferences(Account_Information, MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply{
            putString(USER_NAME, user.name)
            putString(USER_MOBILE, user.mobile)
            putString(USER_EMAIL, user.email)
            putString(USER_PASSWORD, user.password)
            startActivity(intent)
        }
    }

    companion object{
        const val Account_Information = "Account information"
    }
}