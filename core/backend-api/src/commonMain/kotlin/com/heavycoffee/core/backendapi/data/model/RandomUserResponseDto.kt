package com.heavycoffee.core.backendapi.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class RandomUserResponseDto(
    val results: List<UserDto>,
    val info: InfoDto
)