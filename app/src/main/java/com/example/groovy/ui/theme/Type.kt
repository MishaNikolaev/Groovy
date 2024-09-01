package com.example.groovy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.groovy.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val monterrat = FontFamily(
    Font(R.font.montserratalternates_regular, FontWeight.Normal),
    Font(R.font.montserratalternates_medium, FontWeight.Medium),
    Font(R.font.montserratalternates_semibold, FontWeight.SemiBold),
    Font(R.font.montserratalternates_bold, FontWeight.Bold),
    Font(R.font.montserratalternates_extrabold, FontWeight.ExtraBold)
)

val roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_black, FontWeight.Medium),
    Font(R.font.roboto_italic, FontWeight.Medium),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)