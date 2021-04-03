package com.example.tutorial.android.ui.auth.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val newUser: Boolean
    //... other data fields that may be accessible to the UI
)