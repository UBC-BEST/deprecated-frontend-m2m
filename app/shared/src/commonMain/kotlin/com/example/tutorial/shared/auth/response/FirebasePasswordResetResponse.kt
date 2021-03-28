package com.example.tutorial.shared.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class FirebasePasswordResetResponse(
    val email: String? = null,
    val requestType: String? = null,
    val code: Int? = null,
    val message: String? = null
)