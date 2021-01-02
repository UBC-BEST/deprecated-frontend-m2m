package com.example.tutorial.shared.auth

import android.content.Context
import android.preference.PreferenceManager
import com.auth0.android.result.Credentials

actual object CredentialsManager {
    private const val PREFERENCES_NAME = "auth0"
    private val ACCESS_TOKEN = "access_token"

    fun saveCredentials(context: Context, credentials: Credentials) {
        val sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        sp!!.edit().putString(ACCESS_TOKEN, credentials.accessToken)
            .apply()
    }

    fun getAccessToken(context: Context): String? {
        val sp = context.getSharedPreferences(
            PREFERENCES_NAME, Context.MODE_PRIVATE)

        return sp!!.getString(ACCESS_TOKEN, null)
    }
}