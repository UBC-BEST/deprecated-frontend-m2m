package com.example.tutorial.android.ui.auth.data


import android.util.Log
import com.example.tutorial.android.ui.auth.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var user: LoggedInUser

    fun login(username: String, password: String): Result<LoggedInUser> {
        // try to sign the user in first
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
                    } else {
                        Log.w("AUTH", it.exception)
                    }
                }
        } catch (e: FirebaseAuthInvalidUserException) {
            Log.w("EXCEPTION", e)
        }.runCatching {
            return Result.Success(user)
        }

        // now try to sign the user up
        try {
            firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AUTH", "createUserWithEmailAndPassword: Successful")
                        val firebaseUser = firebaseAuth.currentUser
                        user = LoggedInUser(firebaseUser!!.uid, username, true)
                        Log.d("USER INFO", user.newUser.toString())
                        Log.d("USER INFO", user.displayName)
                    } else {
                        Log.w("AUTH", it.exception)
                    }
                }
        } catch (e: FirebaseAuthUserCollisionException) {
            Log.w("AUTH", e)
        }.runCatching {
            return Result.Success(user)
        }
        return Result.Error(IOException("Error with authentication"))
    }


    fun logout() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}