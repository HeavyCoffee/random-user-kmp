package com.heavycoffee.core.network

import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger

actual fun Logger(): Logger = Logger.DEFAULT