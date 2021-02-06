package com.example.tutorial.androidApp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class LoginActivity : AppCompatActivity() {
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gclient: GoogleSignInClient

    /**
     * log the user in
     */
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
        gclient = GoogleSignIn.getClient(this, gso)
    }

    /**
     * check if user is logged in or not already when they open the app
     */
    override fun onStart() {
        super.onStart()
    }
}
 # publish branch
