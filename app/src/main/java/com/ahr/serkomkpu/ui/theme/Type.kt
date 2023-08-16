package com.ahr.serkomkpu.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ahr.serkomkpu.R

val poppinsFontFamily = FontFamily(
    Font(resId = R.font.poppins_thin, weight = FontWeight.Thin),
    Font(resId = R.font.poppins_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.poppins_light, weight = FontWeight.Light),
    Font(resId = R.font.poppins_regular, weight = FontWeight.Normal),
    Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
    Font(resId = R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold),
    Font(resId = R.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.poppins_black, weight = FontWeight.Black),
)

val Typography.heading2: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 20.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
    )

val Typography.heading3: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
    )

val Typography.authTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 18.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = redKpu,
    )

val Typography.regularTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 12.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        color = black,
    )

val Typography.labelInputTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = black,
    )

val Typography.placeholderInputTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        color = greyTextFill,
    )

val Typography.textButtonStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 14.sp,
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        letterSpacing = 0.04.sp,
        lineHeight = 22.4.sp,
    )

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)