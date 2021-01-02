package com.example.tutorial.shared.network

import com.example.tutorial.shared.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*

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

    suspend fun addItem(item: String, accessToken: String?): Request {
        return httpClient.post<Request> {
            url {
                host = PRIVATE_ENDPOINT
                parameters.append("todo", item)
            }
            headers.append("Authorization", "Bearer $accessToken")
            headers.append("Content-Type", "text/plain")
            headers.append("Todo", item)
            HttpMethod.Post
        }
    }
}