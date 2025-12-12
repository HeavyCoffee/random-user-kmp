package com.heavycoffee.core.database.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.heavycoffee.core.database.data.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [UserEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}

@Suppress("KotlinNoActualForExpect")
internal expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal fun getDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase = builder
    .setDriver(BundledSQLiteDriver())
    .fallbackToDestructiveMigration(true)
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()