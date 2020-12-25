package com.example.tutorial.shared.network

import com.example.tutorial.shared.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class M2MApi {
    companion object {
        private const val ENDPOINT = "http://localhost:3000/test";
    }
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }

    suspend fun getItems(): List<Todo> {
        print(httpClient.get(ENDPOINT));
        return httpClient.get(ENDPOINT);
    }
}