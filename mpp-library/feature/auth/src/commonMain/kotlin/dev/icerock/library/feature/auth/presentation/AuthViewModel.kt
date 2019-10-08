/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.feature.auth.presentation

import dev.icerock.library.feature.auth.model.UserRepository
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.fields.validate
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.not
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository,
    private val validation: Validation,
    private val strings: Strings,
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<AuthViewModel.EventsListener> {

    val loginField: FormField<String, StringDesc> =
        FormField("", liveBlock(validation::validateLogin))
    val passwordField: FormField<String, StringDesc> =
        FormField("", liveBlock(validation::validatePassword))

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    val submitEnabled: LiveData<Boolean> = loading.not()

    private val fields = listOf(loginField, passwordField)

    fun onSubmitButtonPressed() {
        if (!fields.validate()) return

        val login = loginField.value()
        val password = passwordField.value()

        coroutineScope.launch {
            _loading.value = true
            try {
                userRepository.authorize(
                    login = login,
                    password = password
                )

                onLoginSuccess()
            } catch (error: Exception) {
                onLoginFailed(error)
            } finally {
                _loading.value = false
            }
        }
    }

    private fun onLoginSuccess() {
        eventsDispatcher.dispatchEvent { routeToCompleteAuth() }
    }

    private fun onLoginFailed(error: Exception) {
        val message = error.message?.desc() ?: strings.unknownError.desc()
        eventsDispatcher.dispatchEvent { showError(message) }
    }

    interface Validation {
        fun validateLogin(login: String): StringDesc?
        fun validatePassword(login: String): StringDesc?
    }

    interface Strings {
        val unknownError: StringResource
    }

    interface EventsListener {
        fun showError(message: StringDesc)
        fun routeToCompleteAuth()
    }
}
