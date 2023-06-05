plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version Versions.kotlin
}

android {
    namespace = "com.shamilov.core.android"
    compileSdk = Config.compileSdk
    defaultConfig {
        applicationId = "com.shamilov.core.android"
        minSdk = Config.minSdk
        targetSdk = Config.targetSkd
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    //move to buildSrc
    implementation("androidx.glance:glance-appwidget:1.0.0-beta01")

    implementation(Deps.Android.Compose.activity)
    implementation(Deps.Android.Compose.foundation)
    implementation(Deps.Android.Compose.navigation)
    implementation(Deps.Android.Compose.ui)
    implementation(Deps.Android.Compose.uiTooling)
    implementation(Deps.Android.Compose.uiToolingPreview)
    implementation(Deps.Android.materia3)
    implementation(Deps.Android.koinAndroid)
    implementation(Deps.Kotlin.kotlinxSerialization)
    debugImplementation(Deps.Android.leakCanary)
}