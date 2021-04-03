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
    private lateinit var user: LoggedInUser

    fun signUpNewFirebaseUser(authCallback: AuthCallback, username: String, password: String) {
        try {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AUTH", "createUserWithEmailAndPassword: Successful")
                        val firebaseUser = firebaseAuth.currentUser
                        user = LoggedInUser(firebaseUser!!.uid, username, true)
                        Log.d("USER INFO", user.newUser.toString())
                        Log.d("USER INFO", user.displayName)
                        authCallback.onAuthCallback(user)
                    } else {
                        Log.w("AUTH", it.exception)
                    }
                }
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.w("AUTH", e)
        }
    }

    fun loginExistingFirebaseUser(authCallback: AuthCallback, username: String, password: String) {
        try {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AUTH", "signInWithEmailAndPassword: Successful")
                        val firebaseUser = firebaseAuth.currentUser
                        user = LoggedInUser(firebaseUser!!.uid, username, false)
                        Log.d("USER INFO", user.newUser.toString())
                        Log.d("USER INFO", user.displayName)
                        authCallback.onAuthCallback(user)
                    } else {
                        Log.w("AUTH", it.exception)
                    }
                }
        } catch (e: FirebaseAuthInvalidUserException) {
            Log.w("EXCEPTION", e)
        }
    }

    fun logout() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}