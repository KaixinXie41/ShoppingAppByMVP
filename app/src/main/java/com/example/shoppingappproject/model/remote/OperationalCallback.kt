package com.example.shoppingappproject.model.remote

interface OperationalCallback {
    fun onSuccess(message:String)
    fun onFailure(message:String)
}