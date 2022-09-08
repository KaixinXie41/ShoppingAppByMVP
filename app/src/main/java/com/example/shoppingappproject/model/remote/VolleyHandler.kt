package com.example.shoppingappproject.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.shoppingappproject.model.remote.data.user.User
import com.example.shoppingappproject.model.remote.Constants.BASE_URL
import com.example.shoppingappproject.model.remote.Constants.LOGIN
import com.example.shoppingappproject.model.remote.Constants.REGISTRATION
import org.json.JSONObject
import java.lang.Exception

class VolleyHandler (private val context:Context) {

    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private lateinit var request: JsonObjectRequest


    //Registration Part
    fun userRegistration(user: User, callback: OperationalCallback): String {
        val url = "$BASE_URL$REGISTRATION"
        val data = JSONObject()
        var message: String? = null

        data.apply {
            user.apply {
                put("name", name)
                put("mobile_no", mobile)
                put("email_address", email)
                put("password", password)
            }
        }
        request =
            object : JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
                message = response.getString("message")
                Log.i("tag", message.toString())
                callback.onSuccess(message.toString())
            }, {
                it.printStackTrace()
                Log.i("tag", message.toString())
                callback.onFailure(it.toString())
            }) {}
        requestQueue.add(request)
        return message.toString()
    }

    fun userAnalyticsRegistration(user: User, callback: OperationalCallback): String {
        val url = "$BASE_URL$REGISTRATION"
        val data = JSONObject()
        var message: String? = null

        data.apply {
            user.apply {
                put("name", name)
                put("mobile_no", mobile)
                put("email_address", email)
                put("password", password)
            }
        }
        request =
            object : JsonObjectRequest(Request.Method.GET, url, data, { response: JSONObject ->
                message = response.getString("message")
                Log.i("tag", message.toString())
                callback.onSuccess(message.toString())
            }, {
                it.printStackTrace()
                Log.i("tag", message.toString())
                callback.onFailure(it.toString())
            }) {
                override fun getPriority(): Priority {
                    return Priority.LOW
                }
            }
        requestQueue.add(request)
        return message.toString()
    }

    //Login Part
    fun userLogin(user: User, callback: OperationalCallback): String {
        val url = "$BASE_URL$LOGIN"
        val data = JSONObject()
        var message: String? = null
        data.apply {
            user.apply {
                put("email_address", email)
                put("password", password)
            }
        }
        request =
            object : JsonObjectRequest(Request.Method.POST, url, data, { response: JSONObject ->
                message = response.getString("message")
                Log.i("tag", message.toString())

                val status = response.getInt("status")
                if (status == 0) {
                    val userResponse = response.getJSONObject("user")
                    user.name = userResponse.getString("name")
                    user.email = userResponse.getString("email")
                    user.mobile = userResponse.getString("mobile")
                    callback.onSuccess(message.toString())
                } else {
                    callback.onFailure(message.toString())
                    Log.i("tag", "Login Failed")
                }
            }, {
                it.printStackTrace()
                callback.onFailure(message.toString())
            }){}
        requestQueue.add(request)
        return message.toString()
    }
}