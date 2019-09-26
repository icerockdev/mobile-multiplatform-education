/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.domain.model

import dev.icerock.library.domain.entity.User
import dev.icerock.library.domain.entity.toDomain
import dev.icerock.moko.network.generated.apis.UserApi
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

class UserRepository internal constructor(
    private val userApi: UserApi,
    private val keyValueStorage: KeyValueStorage
) {
    suspend fun authorize(login: String, password: String): User {
        val credentials = buildCredentials(login = login, password = password)
        keyValueStorage.credentials = credentials
        try {
            return userApi.restApi2MyselfGet().toDomain()
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
