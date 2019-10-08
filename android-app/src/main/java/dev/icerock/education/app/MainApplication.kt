package dev.icerock.education.app

import android.app.Application
import android.content.Context
import com.russhwolf.settings.AndroidSettings
import dev.icerock.library.di.Factory

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val factory = Factory(
            baseUrl = "https://jira.icerockdev.com",
            settings = AndroidSettings(delegate = getSharedPreferences("app", Context.MODE_PRIVATE))
        )
    }
}