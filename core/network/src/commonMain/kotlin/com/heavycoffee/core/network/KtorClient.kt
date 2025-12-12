package com.heavycoffee.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

fun configureDefaultHttpClient(
    baseUrl: String,
    requestTimeoutMs: Long = 20000L,
    socketTimeoutMs: Long = 20000L,
    logger: Logger = Logger(),
    logLevel: LogLevel = LogLevel.ALL,
    jsonConfig: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }
): HttpClientConfig<*>.() -> Unit = {
    defaultRequest {
        url(baseUrl)
    }
    install(HttpTimeout) {
        requestTimeoutMillis = requestTimeoutMs
        socketTimeoutMillis = socketTimeoutMs
    }
    install(ContentNegotiation) { json(jsonConfig) }
    install(Logging) {
        this.logger = logger
        level = logLevel
    }
}