pluginManagement {
    repositories {
        jcenter()
        google()

        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
    }
    resolutionStrategy.eachPlugin {
        useModule(Deps.plugins[requested.id.id] ?: return@eachPlugin)
    }
}

enableFeaturePreview("GRADLE_METADATA")

include(":android-app")
include(":mpp-library")

rootProject.name = "Mobile MultiPlatform"