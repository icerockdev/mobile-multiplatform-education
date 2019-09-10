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

setupFramework(listOf(
        MultiPlatformLibrary(
                android = null,
                common = "dev.icerock.moko:core:0.1.0",
                iosX64 = "dev.icerock.moko:core-iosx64:0.1.0",
                iosArm64 = "dev.icerock.moko:core-iosarm64:0.1.0"
        )
   )
)

dependencies {
    val kotlin_version: String by extra

    "commonMainImplementation"("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    "commonMainApi"("dev.icerock.moko:core:0.1.0")
}
