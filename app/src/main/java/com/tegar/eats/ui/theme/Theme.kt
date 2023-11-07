package com.tegar.eats.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.tegar.eats.utils.CustomColorsPalette
import com.tegar.eats.utils.LocalCustomColorsPalette

private val DarkColorScheme = darkColorScheme(
    primary = Orange,
    secondary = PurpleGrey80,
    surface = Color.Transparent,

    tertiary = Pink80,
    onSecondaryContainer = Orange,
    onSurface = LightGray,
    onSurfaceVariant = LightGray,

    )

private val LightColorScheme = lightColorScheme(
    primary = Orange,

    secondary = PurpleGrey40,
    surface = Color.Transparent,

    secondaryContainer = Color.White,
    onSecondaryContainer = Orange,
    onSurface = LightGray,
    onSurfaceVariant = LightGray,
    background = Color.White

    /* Other default colors to override
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val OnLightCustomColorsPalette = CustomColorsPalette(
    costumeSpecialBackground = white80,

    costumeRegularbodyText = Color(0xff999999),
    costumeRegularTitleText = Color.Black,
    costumeWhiteBlack = Color.Black,
    costumeBlackWhite = Color.White,
    costumeSpecialColor = DarkBlue,
    costumeBorderColor = white60,
    costumeCardColor = Color.White,


    )

val OnDarkCustomColorsPalette = CustomColorsPalette(
    costumeSpecialBackground = grayDark,
    costumeRegularbodyText = Color.White,
    costumeRegularTitleText = Color.White,
    costumeWhiteBlack = Color.White,
    costumeBlackWhite = Color.Black,

    costumeSpecialColor = LightBlue,
    costumeBorderColor = Gray,

    costumeCardColor = grayDark,

)

@Composable
fun EatsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val customColorsPalette =
        if (darkTheme) OnDarkCustomColorsPalette
        else OnLightCustomColorsPalette

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}