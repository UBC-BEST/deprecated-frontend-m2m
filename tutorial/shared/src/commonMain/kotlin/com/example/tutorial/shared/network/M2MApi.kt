package com.example.tutorial.shared.network

import com.example.tutorial.shared.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class M2MApi {
    companion object {
        private const val ENDPOINT = "https://m2m-gateway.herokuapp.com/test"
        private const val PRIVATE_ENDPOINT = "https://m2m-gateway.herokuapp.com/test/private"
    }
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }

    suspend fun getItems(): List<Todo> {
        return httpClient.get(ENDPOINT);
    }

    suspend fun addItem(item: String, accessToken: String?) {

        return httpClient.post(PRIVATE_ENDPOINT)
    }
}