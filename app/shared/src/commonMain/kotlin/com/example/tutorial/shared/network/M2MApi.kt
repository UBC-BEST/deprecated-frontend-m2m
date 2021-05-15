package com.example.tutorial.shared.network

import com.example.tutorial.shared.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

const val BASE_URL = "https://m2m-gateway.herokuapp.com"
const val ENDPOINT_TEST = "/test"
const val ENDPOINT_USER = "/user"

class M2MApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }

    private suspend fun getUser(user: FirebaseUser) {
        return httpClient.get(BASE_URL + ENDPOINT_USER) {
            headers {
                append("Authorization", user.getIdToken() )
            }
        }
    }

    // TODO: Merge both POST and PUT endpoints on the backend
    private suspend fun upsertUser(user: FirebaseUser) {
        return httpClient.post(BASE_URL + ENDPOINT_USER) {
            headers {
                append("Authorization", user.getIdToken() )
            }
        }
    }

    suspend fun getTest(): List<Todo> {
        return httpClient.get("$BASE_URL$ENDPOINT_TEST/private"){
            headers {
                append("Authorization", user.getIdToken() )
            }
        }
    }
}