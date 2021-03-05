package com.example.tutorial.shared.auth.response

import com.example.tutorial.shared.auth.AuthenticationStore
import com.example.tutorial.shared.auth.HttpClientProvider

object FirebaseAuthenticationProvider {
    fun createAuthenticationRemote(): AuthenticationStore =
        AuthenticationStore(
            HttpClientProvider().httpClient
        )
}