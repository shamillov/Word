object Deps {
    object SqlDelight {
        const val core = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val android = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val native = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
    }

    object Android {
        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.composeUi}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.composeFoundation}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
            const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        }
        const val materia3 = "androidx.compose.material3:material3:${Versions.material3}"
    }
}
