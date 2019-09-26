/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library

import com.russhwolf.settings.Settings
import dev.icerock.library.feature.auth.model.UserRepository
import dev.icerock.library.feature.auth.presentation.AuthViewModel
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.library.domain.di.Factory as DomainFactory
import dev.icerock.library.feature.auth.di.Factory as AuthFactory

class Factory(
    settings: Settings,
    baseUrl: String
) {
    private val domainFactory = DomainFactory(
        settings = settings,
        baseUrl = baseUrl
    )

    val authFactory = AuthFactory(
        userRepository = object : UserRepository {
            override suspend fun authorize(login: String, password: String) {
                domainFactory.userRepository.authorize(login = login, password = password)
            }
        },
        authValidation = object : AuthViewModel.Validation {
            override fun validateLogin(login: String): StringDesc? {
                return if (login.isBlank()) MR.strings.validation_login.desc()
                else null
            }

            override fun validatePassword(login: String): StringDesc? {
                return if (login.isBlank()) MR.strings.validation_password.desc()
                else null
            }
        },
        authStrings = object : AuthViewModel.Strings {
            override val unknownError: StringResource = MR.strings.unknown_error
        }
    )
}