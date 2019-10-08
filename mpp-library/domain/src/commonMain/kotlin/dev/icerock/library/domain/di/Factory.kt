/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.domain.di

import com.github.aakira.napier.Napier
import com.russhwolf.settings.Settings
import dev.icerock.library.domain.repository.UserRepository
import dev.icerock.library.domain.storage.KeyValueStorage
import dev.icerock.moko.network.exceptionfactory.HttpExceptionFactory
import dev.icerock.moko.network.exceptionfactory.parser.ErrorExceptionParser
import dev.icerock.moko.network.exceptionfactory.parser.ValidationExceptionParser
import dev.icerock.moko.network.features.ExceptionFeature
import dev.icerock.moko.network.features.TokenFeature
import dev.icerock.moko.network.generated.apis.UserApi
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class Factory(
    val baseUrl: String,
    val settings: Settings
) {
    private val json: Json by lazy {
        @Suppress("EXPERIMENTAL_API_USAGE")
        Json.nonstrict
    }

    private val keyValueStorage: KeyValueStorage by lazy {
        KeyValueStorage(settings)
    }

    private val httpClient: HttpClient by lazy {
        HttpClient {
            install(ExceptionFeature) {
                exceptionFactory = HttpExceptionFactory(
                    defaultParser = ErrorExceptionParser(json),
                    customParsers = mapOf(
                        HttpStatusCode.UnprocessableEntity.value to ValidationExceptionParser(json)
                    )
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(message)
                    }
                }
                level = LogLevel.HEADERS
            }
            install(TokenFeature) {
                tokenHeaderName = "Authorization"
                tokenProvider = object : TokenFeature.TokenProvider {
                    override fun getToken(): String? =
                        keyValueStorage.credentials?.let { "Basic $it" }
                }
            }

            // disable standard BadResponseStatus - exceptionfactory do it for us
            expectSuccess = false
        }
    }

    private val userApi: UserApi by lazy {
        UserApi(
            basePath = baseUrl,
            httpClient = httpClient,
            json = json
        )
    }

    val userRepository: UserRepository by lazy {
        UserRepository(
            userApi = userApi,
            keyValueStorage = keyValueStorage
        )
    }
}
