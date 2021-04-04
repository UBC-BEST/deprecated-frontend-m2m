package com.example.tutorial.android.ui.auth.data


import android.util.Log
import com.example.tutorial.android.ui.auth.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var firebaseAuth: FirebaseAuth

    fun logout() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}