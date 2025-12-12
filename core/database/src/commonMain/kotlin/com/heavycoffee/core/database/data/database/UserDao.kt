package com.heavycoffee.core.database.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heavycoffee.core.database.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserEntity)

    @Query("SELECT count(*) FROM UserEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM UserEntity")
    fun getAllAsFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun getById(id: Long): UserEntity?
}