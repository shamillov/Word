package com.shamilov.core.android.widget.theme

import androidx.compose.runtime.Composable
import androidx.glance.GlanceTheme
import androidx.glance.color.ColorProvider
import androidx.glance.color.colorProviders
import com.shamilov.core.android.ui.theme.dark_background
import com.shamilov.core.android.ui.theme.dark_error
import com.shamilov.core.android.ui.theme.dark_inverseOnSurface
import com.shamilov.core.android.ui.theme.dark_inversePrimary
import com.shamilov.core.android.ui.theme.dark_inverseSurface
import com.shamilov.core.android.ui.theme.dark_onBackground
import com.shamilov.core.android.ui.theme.dark_onError
import com.shamilov.core.android.ui.theme.dark_onErrorContainer
import com.shamilov.core.android.ui.theme.dark_onPrimary
import com.shamilov.core.android.ui.theme.dark_onPrimaryContainer
import com.shamilov.core.android.ui.theme.dark_onSecondary
import com.shamilov.core.android.ui.theme.dark_onSecondaryContainer
import com.shamilov.core.android.ui.theme.dark_onSurface
import com.shamilov.core.android.ui.theme.dark_onSurfaceVariant
import com.shamilov.core.android.ui.theme.dark_onTertiary
import com.shamilov.core.android.ui.theme.dark_onTertiaryContainer
import com.shamilov.core.android.ui.theme.dark_outline
import com.shamilov.core.android.ui.theme.dark_primary
import com.shamilov.core.android.ui.theme.dark_primaryContainer
import com.shamilov.core.android.ui.theme.dark_secondary
import com.shamilov.core.android.ui.theme.dark_secondaryContainer
import com.shamilov.core.android.ui.theme.dark_surface
import com.shamilov.core.android.ui.theme.dark_surfaceVariant
import com.shamilov.core.android.ui.theme.dark_tertiary
import com.shamilov.core.android.ui.theme.dark_tertiaryContainer
import com.shamilov.core.android.ui.theme.light_background
import com.shamilov.core.android.ui.theme.light_error
import com.shamilov.core.android.ui.theme.light_inverseOnSurface
import com.shamilov.core.android.ui.theme.light_inversePrimary
import com.shamilov.core.android.ui.theme.light_inverseSurface
import com.shamilov.core.android.ui.theme.light_onBackground
import com.shamilov.core.android.ui.theme.light_onError
import com.shamilov.core.android.ui.theme.light_onErrorContainer
import com.shamilov.core.android.ui.theme.light_onPrimary
import com.shamilov.core.android.ui.theme.light_onPrimaryContainer
import com.shamilov.core.android.ui.theme.light_onSecondary
import com.shamilov.core.android.ui.theme.light_onSecondaryContainer
import com.shamilov.core.android.ui.theme.light_onSurface
import com.shamilov.core.android.ui.theme.light_onSurfaceVariant
import com.shamilov.core.android.ui.theme.light_onTertiary
import com.shamilov.core.android.ui.theme.light_onTertiaryContainer
import com.shamilov.core.android.ui.theme.light_outline
import com.shamilov.core.android.ui.theme.light_primary
import com.shamilov.core.android.ui.theme.light_primaryContainer
import com.shamilov.core.android.ui.theme.light_secondary
import com.shamilov.core.android.ui.theme.light_secondaryContainer
import com.shamilov.core.android.ui.theme.light_surface
import com.shamilov.core.android.ui.theme.light_surfaceVariant
import com.shamilov.core.android.ui.theme.light_tertiary
import com.shamilov.core.android.ui.theme.light_tertiaryContainer

/**
 * @author Shamilov on 31.05.2023
 */

@Composable
internal fun WidgetTheme(
    content: @Composable () -> Unit,
) {
    GlanceTheme(
        colors = colorProviders,
        content = content,
    )
}

private val colorProviders = colorProviders(
    primary = ColorProvider(light_primary, dark_primary),
    onPrimary = ColorProvider(light_onPrimary, dark_onPrimary),
    primaryContainer = ColorProvider(light_primaryContainer, dark_primaryContainer),
    onPrimaryContainer = ColorProvider(light_onPrimaryContainer, dark_onPrimaryContainer),
    secondary = ColorProvider(light_secondary, dark_secondary),
    onSecondary = ColorProvider(light_onSecondary, dark_onSecondary),
    secondaryContainer = ColorProvider(light_secondaryContainer, dark_secondaryContainer),
    onSecondaryContainer = ColorProvider(light_onSecondaryContainer, dark_onSecondaryContainer),
    tertiary = ColorProvider(light_tertiary, dark_tertiary),
    onTertiary = ColorProvider(light_onTertiary, dark_onTertiary),
    tertiaryContainer = ColorProvider(light_tertiaryContainer, dark_tertiaryContainer),
    onTertiaryContainer = ColorProvider(light_onTertiaryContainer, dark_onTertiaryContainer),
    error = ColorProvider(light_error, dark_error),
    errorContainer = ColorProvider(light_onError, dark_onError),
    onError = ColorProvider(light_onError, dark_onError),
    onErrorContainer = ColorProvider(light_onErrorContainer, dark_onErrorContainer),
    background = ColorProvider(light_background, dark_background),
    onBackground = ColorProvider(light_onBackground, dark_onBackground),
    surface = ColorProvider(light_surface, dark_surface),
    onSurface = ColorProvider(light_onSurface, dark_onSurface),
    surfaceVariant = ColorProvider(light_surfaceVariant, dark_surfaceVariant),
    onSurfaceVariant = ColorProvider(light_onSurfaceVariant, dark_onSurfaceVariant),
    outline = ColorProvider(light_outline, dark_outline),
    inverseOnSurface = ColorProvider(light_inverseOnSurface, dark_inverseOnSurface),
    inverseSurface = ColorProvider(light_inverseSurface, dark_inverseSurface),
    inversePrimary = ColorProvider(light_inversePrimary, dark_inversePrimary),
)
