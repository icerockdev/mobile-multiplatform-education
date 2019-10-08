package dev.icerock.library.feature.auth.di

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class AuthModule(
    val factory: Factory
) {
    companion object {
        internal lateinit var moduleFactory: () -> AuthModule

        fun setup(moduleFactory: () -> AuthModule) {
            AuthModule.moduleFactory = moduleFactory
        }
    }
}

val authModule: AuthModule by lazy(AuthModule.moduleFactory)
