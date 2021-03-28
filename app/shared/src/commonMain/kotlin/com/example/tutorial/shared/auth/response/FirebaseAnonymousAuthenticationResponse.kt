package com.example.tutorial.shared.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class FirebaseAnonymousAuthenticationResponse(
    val idToken: String? = null,
    val refreshToken: String? = null,
    val expiresIn: String? = null,
    val localId: String? = null,
    val code: Int? = null,
    val message: String? = null
)