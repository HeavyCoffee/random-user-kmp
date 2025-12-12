package com.heavycoffee.core.backendapi.domain.datasource

import com.heavycoffee.core.backendapi.domain.model.RandomUserParams
import com.heavycoffee.core.backendapi.domain.model.User

interface RandomUserDataSource {
    suspend fun generateUser(params: RandomUserParams): User
}