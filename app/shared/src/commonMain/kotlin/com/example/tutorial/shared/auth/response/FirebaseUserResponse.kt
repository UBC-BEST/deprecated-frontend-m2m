package com.example.tutorial.shared.auth.response

import kotlinx.serialization.Serializable

@Serializable
data class FirebaseUserResponse(
    val kind: String? = null,
    val users: List<FirebaseUser>? = null,
    val code: Int? = null,
    val message: String? = null
)