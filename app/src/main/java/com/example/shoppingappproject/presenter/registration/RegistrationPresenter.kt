package com.example.shoppingappproject.presenter.registration

import com.example.shoppingappproject.model.remote.OperationalCallback
import com.example.shoppingappproject.model.remote.VolleyHandler
import com.example.shoppingappproject.model.remote.data.user.User

class RegistrationPresenter(
    private val volleyHandler: VolleyHandler,
    private val registrationView: RegistrationMVP.RegistrationView)
    :RegistrationMVP.RegistrationPresenter {
    override fun registerUser(user: User): String {
        registrationView.onLoad(true)
        val message = volleyHandler.userRegistration(user, object : OperationalCallback {
            override fun onSuccess(message: String) {
                registrationView.onLoad(false)
                registrationView.setResult(message)
            }

            override fun onFailure(message: String) {
                registrationView.onLoad(false)
                registrationView.setResult(message)
            }

        })
        return message
    }

    override fun registerUserAnalytics(user: User): String {
        registrationView.onLoad(true)
        val message = volleyHandler.userAnalyticsRegistration(user, object : OperationalCallback {
            override fun onSuccess(message: String) {
                registrationView.onLoad(false)
                registrationView.setResult(message)
            }

            override fun onFailure(message: String) {
                registrationView.onLoad(false)
                registrationView.setResult(message)
            }

        })
        return message
    }
}