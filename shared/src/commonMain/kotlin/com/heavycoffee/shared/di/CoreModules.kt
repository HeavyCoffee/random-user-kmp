package com.heavycoffee.shared.di

import com.heavycoffee.core.backendapi.di.koinBackendApiModule
import com.heavycoffee.core.database.di.koinDatabaseModule
import com.heavycoffee.core.network.di.koinNetworkModule

internal fun coreModules() = listOf(
    koinNetworkModule,
    koinBackendApiModule,
    koinDatabaseModule
)