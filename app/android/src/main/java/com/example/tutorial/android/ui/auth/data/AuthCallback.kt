package com.example.tutorial.android.ui.auth.data


import com.example.tutorial.android.ui.auth.data.model.LoggedInUser


interface AuthCallback {
    fun onAuthCallback(loggedInUser: LoggedInUser)
}