package com.example.tutorial.shared.auth

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.result.Credentials

class AuthenticationHandler(val context: Context) : AuthCallback {
    companion object {
        const val text = "Oops something went wrong!"
    }
    override fun onFailure(dialog: Dialog) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(exception: AuthenticationException) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(credentials: Credentials) {
        CredentialsManager.saveCredentials(credentials)
    }
}