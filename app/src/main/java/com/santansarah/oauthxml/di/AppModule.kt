package com.santansarah.oauthxml.di

import android.util.Log
import com.santansarah.oauthxml.data.remote.OAuthApi
import com.santansarah.oauthxml.data.remote.UserApi
import com.santansarah.oauthxml.ui.OAuthManager
import com.santansarah.oauthxml.ui.main.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { provideKtorClient() }
    single { OAuthApi(get()) }
    single { UserApi(get()) }
    single { OAuthManager(get()) }

    viewModel { MainViewModel(get(), get()) }
}

fun provideKtorClient(): HttpClient = HttpClient(Android) {
    engine {
        connectTimeout = 3000
    }
    expectSuccess = true
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("HTTP Client", message)
            }
        }
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
}