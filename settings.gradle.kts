pluginManagement {
    repositories {
        jcenter()
        google()

        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
    }
    resolutionStrategy.eachPlugin {
        val plugins = mapOf(
            "com.android.application" to "com.android.tools.build:gradle:3.5.0",
            "com.android.library" to "com.android.tools.build:gradle:3.5.0",
            "org.jetbrains.kotlin.multiplatform" to "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}",
            "kotlin-android" to "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}",
            "kotlin-android-extensions" to "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        )

        useModule(plugins[requested.id.id] ?: return@eachPlugin)
    }
}


enableFeaturePreview("GRADLE_METADATA")

include(":android-app")
include(":mpp-library")

rootProject.name = "Mobile MultiPlatform"