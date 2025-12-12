package com.heavycoffee.core.database.data.datasource

import com.heavycoffee.core.database.data.database.UserDao
import com.heavycoffee.core.database.data.model.UserEntity
import com.heavycoffee.core.database.domain.datasource.UserDataSource
import com.heavycoffee.core.database.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

internal class UserDataSourceImpl(
    private val dao: UserDao
) : UserDataSource {
    override suspend fun count(): Int {
        return dao.count()
    }

    override suspend fun getUsersFlow(): Flow<List<User>> {
        return dao.getAllAsFlow()
            .map { it.map { user -> user.toDomain() } }
    }

    override suspend fun addUser(user: User) {
        return dao.insert(UserEntity.fromDomain(user))
    }

    override suspend fun getUserById(id: Long): User? {
        return dao.getById(id)?.toDomain()
    }
}