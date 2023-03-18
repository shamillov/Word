plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Versions.gradlePlugin).apply(false)
    id("com.android.library").version(Versions.gradlePlugin).apply(false)
    kotlin("android").version(Versions.kotlin).apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
}

buildscript {
    dependencies {
        classpath(GradlePlugins.sqlDelight)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
