package org.infomericainc.infod2a.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.infomericainc.infod2a.chat.PlatformType
import org.infomericainc.infod2a.theme.PoppinsFont

@Composable
fun ChatTopBar(
    platformType: PlatformType,
    title: String,
    navigationIcon: ImageVector = Icons.Rounded.ArrowBack,
    onNavigationClick : () -> Unit,
    actions : @Composable RowScope.() -> Unit = { }
) {

    val isAndroid by remember(key1 = platformType) {
        mutableStateOf(
            (platformType == PlatformType.Android) || (platformType == PlatformType.Android)
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                if (isAndroid) 40.dp else 60.dp
            )
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(
                horizontal = if (isAndroid) 10.dp else 30.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavigationClick
        ) {
            Icon(imageVector = navigationIcon, contentDescription = "")
        }
        Text(
            text = title,
            fontSize = if (isAndroid) 16.sp else 20.sp,
            fontFamily = PoppinsFont(),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier
        ) {
            actions()
        }

    }
}