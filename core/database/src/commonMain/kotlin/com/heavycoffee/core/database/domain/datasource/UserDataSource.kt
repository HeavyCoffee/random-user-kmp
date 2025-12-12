package com.heavycoffee.core.database.domain.datasource

import com.heavycoffee.core.database.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun count(): Int

    suspend fun getUsersFlow(): Flow<List<User>>

    suspend fun addUser(user: User)

    suspend fun getUserById(id: Long): User?
}