package dev.icerock.library.di

import dev.icerock.library.feature.auth.presentation.AuthViewModel
import dev.icerock.moko.resources.desc.StringDesc

object Validations : AuthViewModel.Validation {
    override fun validateLogin(login: String): StringDesc? {
        return null
    }

    override fun validatePassword(login: String): StringDesc? {
        return null
    }
}