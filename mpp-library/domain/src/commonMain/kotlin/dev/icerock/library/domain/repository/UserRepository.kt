package dev.icerock.library.domain.repository

import dev.icerock.library.domain.storage.KeyValueStorage
import dev.icerock.moko.network.generated.apis.UserApi
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class UserRepository internal constructor(
    private val userApi: UserApi,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun authorize(login: String, password: String) {
        keyValueStorage.credentials = buildCredentials(login = login, password = password)
        try {
            userApi.restApi2MyselfGet()
        } catch (error: Exception) {
            keyValueStorage.credentials = null
            throw error
        }
    }

    @UseExperimental(InternalAPI::class)
    private fun buildCredentials(login: String, password: String): String {
        return "$login:$password".encodeBase64()
    }
}