package com.example.tutorial.android.ui.auth.data


import android.util.Log
import com.example.tutorial.android.ui.auth.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var firebaseAuth: FirebaseAuth

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("DEBUG", "signInWithEmailAndPassword: Successful")
                    } else {
                        Log.w("WARN", it.exception)
                    }
                }

            firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("DEBUG", "createUserWithEmailAndPassword: Successful")
                    } else {
                        Log.w("WARN", it.exception)
                    }
                }
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}