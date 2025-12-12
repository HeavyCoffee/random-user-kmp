package com.heavycoffee.core.backendapi.data.api

import com.heavycoffee.core.backendapi.data.model.RandomUserResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

internal interface RandoUserApi {
    suspend fun generateRandomUsers(
        gender: String?,
        nat: List<String>?,
        results: Int
    ): RandomUserResponseDto
}

internal class RandoUserApiImpl(
    private val client: HttpClient
) : RandoUserApi {
    override suspend fun generateRandomUsers(
        gender: String?,
        nat: List<String>?,
        results: Int
    ): RandomUserResponseDto {
        return client.get("api/") {
            url {
                parameters.append("results", "$results")
                gender?.let { parameters.append("gender", it) }
                nat?.takeIf { it.isNotEmpty() }?.let { parameters.appendAll("nat", it) }
            }
        }.body()
    }
}