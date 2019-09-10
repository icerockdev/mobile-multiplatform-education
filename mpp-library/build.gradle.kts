plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

setupFramework(
    listOf(
        Deps.Libs.MultiPlatform.mokoCore,
        Deps.Libs.MultiPlatform.mokoMvvm,
        Deps.Libs.MultiPlatform.mokoResources
    )
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.mokoCore)
    mppLibrary(Deps.Libs.MultiPlatform.mokoMvvm)
    mppLibrary(Deps.Libs.MultiPlatform.mokoResources)

    androidLibrary(Deps.Libs.Android.lifecycle)
}
