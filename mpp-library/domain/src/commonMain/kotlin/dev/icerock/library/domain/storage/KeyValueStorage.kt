package dev.icerock.library.domain.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableString

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class KeyValueStorage(settings: Settings) {
    var credentials: String? by settings.nullableString("credentials")
}