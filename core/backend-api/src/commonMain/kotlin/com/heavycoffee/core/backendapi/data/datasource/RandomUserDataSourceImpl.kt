package com.heavycoffee.core.backendapi.data.datasource

import com.heavycoffee.core.backendapi.data.api.RandoUserApi
import com.heavycoffee.core.backendapi.domain.datasource.RandomUserDataSource
import com.heavycoffee.core.backendapi.domain.model.RandomUserParams
import com.heavycoffee.core.backendapi.domain.model.User

internal class RandomUserDataSourceImpl(
    private val api: RandoUserApi
) : RandomUserDataSource {
    override suspend fun generateUser(params: RandomUserParams): User {
        return api.generateRandomUsers(
            gender = params.gender?.name,
            nat = params.nationality?.map { it.code },
            results = 1
        ).results.firstOrNull()?.toDomain() ?: throw Exception("Failed to generate user")
    }
}