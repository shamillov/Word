import java.net.URI

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://androidx.dev/snapshots/latest/artifacts/repository") }
    }
}

rootProject.name = "Core"
include(":androidApp")
include(":core")