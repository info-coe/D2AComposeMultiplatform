package org.infomericainc.infod2a.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import infod2a.composeapp.generated.resources.Res
import infod2a.composeapp.generated.resources.allFontResources
import infod2a.composeapp.generated.resources.pop_bold
import infod2a.composeapp.generated.resources.pop_med
import infod2a.composeapp.generated.resources.pop_reg
import infod2a.composeapp.generated.resources.pop_semi
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFont() = FontFamily(
    Font(Res.font.pop_bold, FontWeight.Bold),
    Font(Res.font.pop_semi, FontWeight.SemiBold),
    Font(Res.font.pop_med, FontWeight.Medium),
    Font(Res.font.pop_reg, FontWeight.Normal)
)

