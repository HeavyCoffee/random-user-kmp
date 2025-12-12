package com.heavycoffee.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heavycoffee.core.database.data.database.AppDatabase
import com.heavycoffee.core.database.data.database.DatabaseConfigs

internal fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DatabaseConfigs.DB_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}