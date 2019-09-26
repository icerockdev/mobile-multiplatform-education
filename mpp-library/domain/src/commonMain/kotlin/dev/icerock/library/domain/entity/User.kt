/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.domain.entity

import dev.icerock.moko.network.generated.models.UserResponse

data class User(
    val name: String,
    val email: String,
    val displayName: String
)

internal fun UserResponse.toDomain() = User(
    name = name,
    email = emailAddress,
    displayName = displayName ?: "no name"
)
