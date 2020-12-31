package com.example.tutorial.androidApp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.result.Credentials
import com.example.tutorial.shared.auth.CredentialsManager

class AuthenticationHandler(val context: Context) : AuthCallback, AppCompatActivity() {
    companion object {
        const val text = "Oops something went wrong!"
    }
    override fun onFailure(dialog: Dialog) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(exception: AuthenticationException) {
        Toast.makeText(context, "$text: $exception", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(credentials: Credentials) {
        CredentialsManager.saveCredentials(credentials)
        runOnUiThread {
            var intent = Intent(this@AuthenticationHandler, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
