package com.heavycoffee.core.backendapi.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class InfoDto(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)