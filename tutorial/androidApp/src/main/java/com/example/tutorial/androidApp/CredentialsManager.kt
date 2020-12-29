package com.example.tutorial.androidApp

import android.content.Context
import com.auth0.android.result.Credentials

object CredentialsManager {
    private var context: Context? = null
    private val PREFERENCES_NAME = "auth0"
    private val ACCESS_TOKEN = "access_token"

    fun setContext(context: Context) {
        this.context = context
    }

    fun saveCredentials(context: Context, credentials: Credentials) {
        val sp = context.getSharedPreferences(
            PREFERENCES_NAME, Context.MODE_PRIVATE)

        sp!!.edit().putString(ACCESS_TOKEN, credentials.accessToken)
            .apply()
    }

    fun getAccessToken(context: Context): String? {
        val sp = context.getSharedPreferences(
            PREFERENCES_NAME, Context.MODE_PRIVATE)

        return sp!!.getString(ACCESS_TOKEN, null)
    }
}
