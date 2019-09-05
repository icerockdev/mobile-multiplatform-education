import dev.icerock.moko.core.Timer
import dev.icerock.moko.core.getCurrentMilliSeconds

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

object HelloWorld {
    fun print() {
        println("hello common world")
    }

    fun start() {
        val timer = Timer(intervalMilliSeconds = 1000) {
            val currentTime = getCurrentMilliSeconds()
            println("now $currentTime milliseconds")
            true
        }
        timer.start()
    }
}