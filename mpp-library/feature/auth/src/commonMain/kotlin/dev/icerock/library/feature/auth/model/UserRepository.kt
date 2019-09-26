/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.feature.auth.model

interface UserRepository {
    suspend fun authorize(login: String, password: String)
}
