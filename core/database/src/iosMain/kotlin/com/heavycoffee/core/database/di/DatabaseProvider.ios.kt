package com.heavycoffee.core.database.di

import androidx.room.RoomDatabase
import com.heavycoffee.core.database.data.database.AppDatabase
import com.heavycoffee.core.database.data.database.getDatabase
import com.heavycoffee.core.database.getDatabaseBuilder
import org.koin.core.module.Module

internal actual fun Module.provideDatabase() {
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
    single<AppDatabase> { getDatabase(builder = get()) }
}