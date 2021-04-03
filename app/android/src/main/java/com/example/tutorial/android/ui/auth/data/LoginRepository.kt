package com.example.tutorial.android.ui.auth.data

import android.util.Log
import com.example.tutorial.android.ui.auth.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, isNew: Boolean): Boolean {
        // handle login
        val authCallback = object : AuthCallback {
            override fun onAuthCallback(loggedInUser: LoggedInUser) {
                Log.d("USER INFO: NAME?", loggedInUser.displayName)
                Log.d("USER INFO: UUID?", loggedInUser.userId)
                Log.d("USER INFO: NEW OR RETURNING?", loggedInUser.newUser.toString())
                setLoggedInUser(loggedInUser)
            }
        }
        if (isNew) {
            try {
                dataSource.signUpNewFirebaseUser(authCallback, username, password).run {
                    return true
                }
            } catch (e: Exception) {
                Log.e("ERROR LOGGING IN", e.toString())
                return false
            }
        } else {
            try {
                dataSource.loginExistingFirebaseUser(authCallback, username, password).run {
                    return true
                }
            } catch (e: Exception) {
                Log.e("ERROR LOGGING IN", e.toString())
                return false
            }
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}