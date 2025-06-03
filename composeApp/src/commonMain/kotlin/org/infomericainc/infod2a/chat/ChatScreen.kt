package org.infomericainc.infod2a.chat

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infod2a.composeapp.generated.resources.Res
import infod2a.composeapp.generated.resources.compose_multiplatform
import org.infomericainc.infod2a.chat.components.ChatTopBar
import org.infomericainc.infod2a.theme.PoppinsFont
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ChatScreen() {
    MaterialTheme {
        val platformType by remember {
            mutableStateOf(getPlatform())
        }

        val isAndroid by remember(key1 = platformType) {
            mutableStateOf(
                (platformType == PlatformType.Android) || (platformType == PlatformType.Android)
            )
        }

        var userInput by remember {
            mutableStateOf("")
        }

        var conversation = remember {
            mutableStateListOf<Pair<Boolean, String>>(
                Pair(true, "Hey there"),
                Pair(false, "How are you! How can i help you today."),
                )
        }

        Scaffold(
            topBar = {
                ChatTopBar(
                    platformType = platformType,
                    onNavigationClick = {

                    },
                    title = "NxtGen Agent",
                    actions = {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(imageVector = Icons.Rounded.Settings, contentDescription = "")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            horizontal = 160.dp
                        )
                        .padding(
                            bottom = 20.dp
                        )
                ) {
                    item {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                    }
                    items(
                        conversation
                    ) {
                        if (it.first) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    it.second,
                                    fontFamily = PoppinsFont(),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 20.dp
                                        )
                                        .clip(CircleShape)
                                        .background(
                                            MaterialTheme.colorScheme.surfaceContainer
                                        )
                                        .padding(
                                            horizontal = 20.dp,
                                            vertical = 10.dp
                                        )
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = 20.dp
                                    ),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Top
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.compose_multiplatform),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(30.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(
                                            top = 5.dp
                                        )
                                ) {
                                    Text(
                                        "NxtGen Agent",
                                        fontFamily = PoppinsFont(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .padding(
                                                start = 10.dp
                                            )
                                    )
                                    Text(
                                        it.second,
                                        fontFamily = PoppinsFont(),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        modifier = Modifier
                                            .padding(
                                                start = 10.dp
                                            )
                                            .padding(
                                                top = 5.dp
                                            )
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(
                            bottom = 20.dp
                        )
                        .padding(
                            horizontal = if (isAndroid) 20.dp else 130.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(Icons.Rounded.Add, ":")
                    }

                    Box(
                        modifier = Modifier
                            .weight(.9f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        TextField(
                            value = userInput,
                            onValueChange = {
                                userInput = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 10.dp
                                )
                                .animateContentSize(),
                            textStyle = TextStyle(
                                fontFamily = PoppinsFont(),
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter your message",
                                    fontFamily = PoppinsFont(),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            },
                            minLines = 1,
                            maxLines = 8,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }

                    IconButton(
                        onClick = {
                            conversation.add(
                                Pair(
                                    true,userInput
                                )
                            )
                            userInput = ""
                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(Icons.Rounded.Send, ":")
                    }
                }
            }
        }
    }
}