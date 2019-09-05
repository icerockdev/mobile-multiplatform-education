package com.icerockdev.android_app

import AndroidHelloWorld
import HelloWorld
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.icerock.moko.core.getCurrentMilliSeconds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HelloWorld.print()
        AndroidHelloWorld.print()

        println("start time: ${getCurrentMilliSeconds()}")

        HelloWorld.start()
    }
}
