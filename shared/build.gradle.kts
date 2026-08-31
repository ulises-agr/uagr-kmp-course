// build.gradle - shared
// Copyright (c) 2026. All rights reserved
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {

    jvmToolchain(jdkVersion = 21)

    // Config for devices ios
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = false
        }
    }

    android {
        namespace = "com.uagr.kmp.course.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        androidResources {
            enable = true
        }

        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        // SharedUI module
        commonMain.dependencies {
            // Bundle
            implementation(dependencyNotation = libs.bundles.sharedUI.commons.libs)
            // Bundle
            implementation(dependencyNotation = libs.bundles.sharedLogic.commons.libs)
        }
        // Android
        androidMain.dependencies {
            // Libs
            implementation(dependencyNotation = libs.bundles.sharedUI.android.libs)
        }
        // Ios
        iosMain.dependencies {}
        // SharedUI test
        commonTest.dependencies {
            implementation(dependencyNotation = libs.bundles.sharedUI.commonsTest.libs)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}
