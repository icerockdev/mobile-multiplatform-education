import java.net.URI

pluginManagement {
    repositories {
        jcenter()
        google()

        maven { url = URI("https://dl.bintray.com/icerockdev/plugins") }
    }
    resolutionStrategy.eachPlugin {
        val kotlin_version: String by extra

        val plugins = mapOf(
            "com.android.application" to "com.android.tools.build:gradle:3.5.0",
            "com.android.library" to "com.android.tools.build:gradle:3.5.0",
            "org.jetbrains.kotlin.multiplatform" to "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            "kotlin-android" to "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            "kotlin-android-extensions" to "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
            "dev.icerock.mobile.multiplatform" to "dev.icerock:mobile-multiplatform:0.2.0"
        )

        useModule(plugins[requested.id.id] ?: return@eachPlugin)
    }
}


enableFeaturePreview("GRADLE_METADATA")

include(":android-app")
include(":mpp-library")

rootProject.name = "Mobile MultiPlatform"