package com.tegar.eats.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val costumeSpecialBackground: Color = Color.Unspecified,
    val costumeRegularbodyText  : Color = Color.Unspecified,
    val costumeRegularTitleText : Color = Color.Unspecified,
    val costumeWhiteBlack : Color = Color.Unspecified,
    val costumeBlackWhite : Color = Color.Unspecified,
    val costumeSpecialColor : Color = Color.Unspecified,
    val costumeBorderColor : Color = Color.Unspecified,
    val costumeCardColor: Color = Color.Unspecified,
    val costumeFontSubtitleColor : Color = Color.Unspecified

    )

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }