package com.tegar.eats.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tegar.eats.R


val ManropeFamiliy = FontFamily(
    Font(R.font.manrope_regular),
    Font(R.font.manrope_medium),
    Font(R.font.manrope_semibold),
    Font(R.font.manrope_semibold),

    )

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    displaySmall = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ManropeFamiliy,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)