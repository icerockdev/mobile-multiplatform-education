/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
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
        Deps.Libs.MultiPlatform.mokoResources,
        Deps.Libs.MultiPlatform.settings,
        Modules.MultiPlatform.Feature.auth
    )
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.mokoCore)
    mppLibrary(Deps.Libs.MultiPlatform.mokoMvvm)
    mppLibrary(Deps.Libs.MultiPlatform.mokoResources)

    mppModule(Modules.MultiPlatform.domain)
    mppModule(Modules.MultiPlatform.Feature.auth)

    androidLibrary(Deps.Libs.Android.lifecycle)

    mppModule(Modules.MultiPlatform.domain)
    mppModule(Modules.MultiPlatform.Feature.auth)
}

multiplatformResources {
    multiplatformResourcesPackage = "dev.icerock.library"
}
