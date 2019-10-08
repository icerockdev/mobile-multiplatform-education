package dev.icerock.library.di

import com.russhwolf.settings.Settings
import dev.icerock.library.MR
import dev.icerock.library.feature.auth.di.AuthModule
import dev.icerock.library.feature.auth.model.UserRepository
import dev.icerock.library.feature.auth.presentation.AuthViewModel
import dev.icerock.moko.resources.StringResource
import dev.icerock.library.domain.di.Factory as DomainFactory
import dev.icerock.library.feature.auth.di.Factory as AuthFactory

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class Factory(
    private val baseUrl: String,
    private val settings: Settings
) {
    private val domainFactory: DomainFactory by lazy {
        DomainFactory(
            baseUrl = baseUrl,
            settings = settings
        )
    }

    val authFactory: AuthFactory by lazy {
        AuthFactory(
            userRepository = object : UserRepository {
                override suspend fun authorize(login: String, password: String) {
                    domainFactory.userRepository.authorize(
                        login = login,
                        password = password
                    )
                }
            },
            authValidation = Validations,
            authStrings = object : AuthViewModel.Strings {
                override val unknownError: StringResource = MR.strings.unknown_error
            }
        )
    }

    init {
        AuthModule.setup { AuthModule(factory = authFactory) }
    }
}
