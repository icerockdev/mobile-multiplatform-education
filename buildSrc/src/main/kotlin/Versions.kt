object Versions {
    object Android {
        const val compileSdk = 28
        const val targetSdk = 28
        const val minSdk = 21
    }

    const val kotlin = "1.3.50"

    private const val mokoResources = "0.3.0"
    private const val mokoNetwork = "0.1.0"

    object Plugins {
        const val androidExtensions = Versions.kotlin
        const val mokoResources = Versions.mokoResources
        const val mokoNetwork = Versions.mokoNetwork
    }

    object Libs {
        object Android {
            const val kotlinStdLib = Versions.kotlin
            const val appCompat = "1.1.0"
            const val constraintLayout = "1.1.3"
            const val lifecycle = "2.0.0"
        }

        object MultiPlatform {
            const val kotlinStdLib = Versions.kotlin
            const val mokoCore = "0.1.0"
            const val mokoMvvm = "0.2.0"
            const val mokoResources = Versions.mokoResources
            const val mokoNetwork = Versions.mokoNetwork
            const val mokoFields = "0.1.0"
            const val mokoPermissions = "0.1.0"
            const val mokoMedia = "0.1.0"
        }
    }
}