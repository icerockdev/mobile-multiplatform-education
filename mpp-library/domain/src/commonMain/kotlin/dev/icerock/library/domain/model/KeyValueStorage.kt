/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.domain.model

import com.russhwolf.settings.Settings
import com.russhwolf.settings.nullableString

class KeyValueStorage(settings: Settings) {
    var credentials by settings.nullableString("credentials")
}