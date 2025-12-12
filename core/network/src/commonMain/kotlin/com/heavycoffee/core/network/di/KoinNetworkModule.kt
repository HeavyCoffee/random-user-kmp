package com.heavycoffee.core.network.di

import com.heavycoffee.core.network.configureDefaultHttpClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val koinNetworkModule = module {
    single<HttpClient> {
        HttpClient(
            configureDefaultHttpClient(
                baseUrl = "https://randomuser.me"
            )
        )
    }
}