package com.example.tutorial.shared.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class FirebaseDeleteUserResponse(
    val code: Int? = null,
    val message: String? = null
)