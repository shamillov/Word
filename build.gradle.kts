import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Versions.gradlePlugin).apply(false)
    id("com.android.library").version(Versions.gradlePlugin).apply(false)
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    kotlin("android").version(Versions.kotlin).apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
}

buildscript {
    dependencies {
        classpath(GradlePlugins.sqlDelight)
    }
}

allprojects {
    apply<DetektPlugin>()
    configure<DetektExtension> {
        config.from(files("$rootDir/config/detekt/detekt.yml"))
        baseline = file("$rootDir/config/detekt/baseline.xml")
        buildUponDefaultConfig = true
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("detektAll", Detekt::class) {
    description = "Run Detekt task for all modules"
    parallel = true
    autoCorrect = false
    setSource(file(projectDir))
    config.from(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")
    buildUponDefaultConfig = true
    reports {
        html.required.set(true)
    }
}
