package com.example.tutorial.shared.auth

import com.example.tutorial.shared.auth.response.*
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

class AuthenticationStore(
    private val httpClient: HttpClient
){

    suspend fun signUp(
        apiKey: String,
        email: String,
        password: String,
        returnSecureToken: Boolean
    ) = handleAuthenticationRequest(apiKey,
        ENDPOINT_SIGN_UP, email, password, returnSecureToken)

    suspend fun signIn(
        apiKey: String,
        email: String,
        password: String,
        returnSecureToken: Boolean
    ) = handleAuthenticationRequest(apiKey,
        ENDPOINT_SIGN_IN, email, password, returnSecureToken)

    suspend fun signInWithCustomToken(
        apiKey: String,
        token: String,
        returnSecureToken: Boolean
    ): FirebaseCustomTokenSignInResponse {
        return try {
            httpClient.post {
                url("$BASE_URL$ENDPOINT_SIGN_IN_WITH_CUSTOM_TOKEN")
                parameter("key", apiKey)
                parameter("token", token)
                parameter("returnSecureToken", returnSecureToken)
            }
        } catch (cause: Throwable) {
            FirebaseCustomTokenSignInResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun signInAnonymously(
        apiKey: String,
        returnSecureToken: Boolean
    ): FirebaseAnonymousAuthenticationResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                url("$BASE_URL$ENDPOINT_SIGN_IN_WITH_CUSTOM_TOKEN")
                parameter("key", apiKey)
                parameter("returnSecureToken", returnSecureToken)
            }.execute()
            Json.decodeFromString(FirebaseAnonymousAuthenticationResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebaseAnonymousAuthenticationResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun resetPassword(
        apiKey: String,
        email: String,
        languageCode: String?
    ): FirebasePasswordResetResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                if (languageCode != null) header("X-Firebase-Locale", languageCode)
                url("$BASE_URL$ENDPOINT_SEND_RESET_PASSWORD_EMAIL")
                parameter("key", apiKey)
                parameter("email", email)
                parameter("requestType", "PASSWORD_RESET")
            }.execute()
            Json.decodeFromString(FirebasePasswordResetResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebasePasswordResetResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun updateEmail(
        apiKey: String,
        idToken: String,
        email: String,
        languageCode: String?,
        returnSecureToken: Boolean
    ): FirebaseUpdateUserResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                if (languageCode != null) header("X-Firebase-Locale", languageCode)
                url("$BASE_URL$ENDPOINT_UPDATE_USER")
                parameter("key", apiKey)
                parameter("idToken", idToken)
                parameter("returnSecureToken", returnSecureToken)
            }.execute()
            Json.decodeFromString(FirebaseUpdateUserResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebaseUpdateUserResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun updatePassword(
        apiKey: String,
        idToken: String,
        password: String,
        languageCode: String?,
        returnSecureToken: Boolean
    ): FirebaseUpdateUserResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                if (languageCode != null) header("X-Firebase-Locale", languageCode)
                url("$BASE_URL$ENDPOINT_UPDATE_USER")
                parameter("key", apiKey)
                parameter("idToken", idToken)
                parameter("returnSecureToken", returnSecureToken)
            }.execute()
            Json.decodeFromString(FirebaseUpdateUserResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebaseUpdateUserResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun verifyResetPassword(
        apiKey: String,
        oobCode: String
    ): FirebasePasswordResetResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                url("$BASE_URL$ENDPOINT_RESET_PASSWORD")
                parameter("key", apiKey)
                parameter("oobCode", oobCode)
            }.execute()
            Json.decodeFromString(FirebasePasswordResetResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebasePasswordResetResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun confirmResetPassword(
        apiKey: String,
        oobCode: String,
        newPassword: String
    ): FirebasePasswordResetResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                url("$BASE_URL$ENDPOINT_RESET_PASSWORD")
                parameter("key", apiKey)
                parameter("oobCode", oobCode)
                parameter("newPassword", newPassword)
            }.execute()
            Json.decodeFromString(FirebasePasswordResetResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebasePasswordResetResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun retrieveUser(
        apiKey: String,
        idToken: String
    ): FirebaseUserResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                url("$BASE_URL$ENDPOINT_RETRIEVE_USER")
                parameter("key", apiKey)
                parameter("idToken", idToken)
            }.execute()
            Json.decodeFromString(FirebaseUserResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebaseUserResponse(
                message = cause.message ?: ""
            )
        }
    }

    suspend fun deleteUser(
        apiKey: String,
        idToken: String
    ): FirebaseDeleteUserResponse {
        return try {
            val response = httpClient.post<HttpStatement> {
                url("$BASE_URL$ENDPOINT_DELETE_USER")
                parameter("key", apiKey)
                parameter("idToken", idToken)
            }.execute()
            Json.decodeFromString(FirebaseDeleteUserResponse.serializer(), response.readText())
        } catch (cause: Throwable) {
            FirebaseDeleteUserResponse(
                message = cause.message ?: ""
            )
        }
    }

    private suspend fun handleAuthenticationRequest(
        apiKey: String,
        endpoint: String,
        email: String,
        password: String,
        returnSecureToken: Boolean
    ): FirebaseAuthenticationResponse {
        return try {
            httpClient.post {
                url("$BASE_URL$endpoint")
                parameter("key", apiKey)
                parameter("email", email)
                parameter("password", password)
                parameter("returnSecureToken", returnSecureToken)
            }
        } catch (cause: Throwable) {
            FirebaseAuthenticationResponse(
                message = cause.message ?: ""
            )
        }
    }

    companion object {
        private const val ENDPOINT_SIGN_UP = "signUp"
        private const val ENDPOINT_SIGN_IN = "signInWithPassword"
        private const val ENDPOINT_SIGN_IN_WITH_CUSTOM_TOKEN = "signInWithCustomToken"
        private const val ENDPOINT_SEND_RESET_PASSWORD_EMAIL = "sendOobCode"
        private const val ENDPOINT_RESET_PASSWORD = "resetPassword"
        private const val ENDPOINT_UPDATE_USER = "update"
        private const val ENDPOINT_RETRIEVE_USER = "lookup"
        private const val ENDPOINT_DELETE_USER = "delete"
        private const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/accounts:"
    }
}