/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.feature.auth.di

import dev.icerock.library.feature.auth.model.UserRepository
import dev.icerock.library.feature.auth.presentation.AuthViewModel
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher

class Factory(
    val userRepository: UserRepository,
    val authValidation: AuthViewModel.Validation,
    val authStrings: AuthViewModel.Strings
) {
    fun createAuthViewModel(eventsDispatcher: EventsDispatcher<AuthViewModel.EventsListener>): AuthViewModel {
        return AuthViewModel(
            userRepository = userRepository,
            validation = authValidation,
            strings = authStrings,
            eventsDispatcher = eventsDispatcher
        )
    }
}