package com.heavycoffee.core.network

import android.util.Log
import io.ktor.client.plugins.logging.Logger

actual fun Logger(): Logger = object : Logger {
    override fun log(message: String) {
        Log.d("Ktor", message)
    }
}