package org.infomericainc.infod2a.presentation.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Transcribe
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import infod2a.composeapp.generated.resources.Res
import infod2a.composeapp.generated.resources.compose_multiplatform
import infod2a.composeapp.generated.resources.logo
import infod2a.composeapp.generated.resources.power
import infod2a.composeapp.generated.resources.sales
import org.infomericainc.infod2a.domain.model.Message
import org.infomericainc.infod2a.presentation.chat.viewmodel.AccountType
import org.infomericainc.infod2a.presentation.chat.viewmodel.ChatCompletionEvent
import org.infomericainc.infod2a.presentation.chat.viewmodel.ChatUiState
import org.infomericainc.infod2a.theme.PoppinsFont
import org.infomericainc.infod2a.util.extension.actualPathName
import org.infomericainc.infod2a.util.extension.isMobileDesktop
import org.infomericainc.infod2a.util.getPlatform
import org.infomericainc.infod2a.util.isAndroidOrIos
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ChatScreen(
    navController: NavController,
    conversations: List<Message>,
    chatUiState: ChatUiState,
    desktopWindowWidth : Int,
    onEvent: (ChatCompletionEvent) -> Unit
) {
    val isAndroidOrIos by remember {
        mutableStateOf(getPlatform().isAndroidOrIos())
    }

    MaterialTheme {
        if (isAndroidOrIos) {
            MobileChatScreen(
                chatUiState = chatUiState,
                conversations = conversations,
                onEvent = onEvent
            )
        } else {
            if (desktopWindowWidth.isMobileDesktop()) {
                MobileChatScreen(
                    chatUiState = chatUiState,
                    conversations = conversations,
                    onEvent = onEvent
                )
            } else {
                DesktopChatScreen(
                    chatUiState = chatUiState,
                    conversations = conversations,
                    onEvent = onEvent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileChatScreen(
    chatUiState: ChatUiState,
    conversations: List<Message>,
    onEvent: (ChatCompletionEvent) -> Unit
) {

    var userInput by remember {
        mutableStateOf("")
    }

    var currentCRM by remember {
        mutableStateOf(AccountType.SALESFORCE.name.actualPathName())
    }

    var showMenu by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(userInput) {
        if (showMenu) {
            showMenu = false
        }
    }

    var bottomHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    val listState = rememberLazyListState()

    LaunchedEffect(conversations) {
        if (conversations.isNotEmpty()) {
            listState.animateScrollToItem(conversations.size)
        }
    }

    val state = rememberModalBottomSheetState()

    if(showMenu) {
        ModalBottomSheet(
            onDismissRequest = {
                showMenu = false
            },
            sheetState = state
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            bottomStart = 0.dp,
                            topEnd = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
                    .padding(
                        all = 10.dp
                    ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {
                            currentCRM =
                                if (currentCRM == AccountType.SALESFORCE.name.actualPathName()) {
                                    AccountType.POWERAPPS.name.actualPathName()
                                } else {
                                    AccountType.SALESFORCE.name.actualPathName()
                                }
                            showMenu = false
                            onEvent(ChatCompletionEvent.ClearConversation)
                        }
                        .padding(
                            all = 10.dp
                        ),
                    color = MaterialTheme.colorScheme.primary,
                    text = "Currently configured as a $currentCRM, Tap here to switch",
                    fontFamily = PoppinsFont(),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
                Text(
                    "Features",
                    fontFamily = PoppinsFont(),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            top = 20.dp
                        )
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 15.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Transcribe,
                        contentDescription = "",
                        modifier = Modifier
                            .size(
                                18.dp
                            )
                    )
                    Text(
                        text = "Live Transcribe",
                        modifier = Modifier
                            .padding(
                                start = 10.dp
                            ),
                        fontFamily = PoppinsFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        letterSpacing = 0.3.sp
                    )
                }

            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                        ) {
                            Image(
                                painter = if (currentCRM == AccountType.SALESFORCE.name.actualPathName()) {
                                    painterResource(Res.drawable.sales)
                                } else {
                                    painterResource(Res.drawable.power)
                                },
                                contentDescription = "",
                                modifier = Modifier
                                    .size(
                                        25.dp
                                    ),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                currentCRM,
                                fontFamily = PoppinsFont(),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp
                                    )
                            )
                        }
                    },
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .padding(
                                    end = 10.dp
                                )
                                .clip(CircleShape)
                                .background(
                                    MaterialTheme.colorScheme.surfaceContainerHigh
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 5.dp
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Black
                            )
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Black
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                showMenu = !showMenu
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                )
                AnimatedVisibility(
                    visible = chatUiState.isResponding,
                    enter = slideInVertically() + expandVertically(),
                    exit = slideOutVertically() + shrinkVertically()
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.Black,
                        trackColor = Color.White
                    )
                }
            }
        },
        modifier = Modifier
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (conversations.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(
                                Res.drawable.logo
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(
                                    80.dp
                                )
                                .height(
                                    80.dp
                                )
                        )
                        Text(
                            text = "Welcome to InfoCRM",
                            fontFamily = PoppinsFont(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Send a hi to start the conversation with our $currentCRM",
                            fontFamily = PoppinsFont(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(
                                    horizontal = 20.dp
                                )
                                .padding(
                                    top = 5.dp
                                )
                                .alpha(.6f)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                bottom = bottomHeight
                            )
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(
                                horizontal = 15.dp
                            ),
                        state = listState
                    ) {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                            )
                        }
                        items(
                            conversations
                        ) {
                            if (it.role == "user") {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        it.content,
                                        fontFamily = PoppinsFont(),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(
                                                bottom = 20.dp
                                            )
                                            .clip(CircleShape)
                                            .background(
                                                MaterialTheme.colorScheme.surfaceContainer
                                            )
                                            .padding(
                                                horizontal = 10.dp,
                                                vertical = 5.dp
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
                                            currentCRM,
                                            fontFamily = PoppinsFont(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .padding(
                                                    start = 10.dp
                                                )
                                        )
                                        Text(
                                            it.content,
                                            fontFamily = PoppinsFont(),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .padding(
                                                    start = 10.dp
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .onGloballyPositioned { placed ->
                            with(density) {
                                bottomHeight = placed.size.height.toDp() + 10.dp
                            }
                        }
                        .padding(
                            bottom = 20.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(
                            Icons.Rounded.Add,
                            ":",
                            modifier = Modifier
                                .size(18.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(
                                horizontal = 10.dp
                            )
                            .weight(.8f,fill = false)
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
                                .animateContentSize()
                                .onPreviewKeyEvent { keyEvent ->
                                    when {
                                        ((keyEvent.key == Key.ShiftLeft) || (keyEvent.key == Key.ShiftLeft)) && (keyEvent.key == Key.Enter) -> {
                                            true
                                        }

                                        keyEvent.key == Key.Enter -> {
                                            if (userInput.isNotEmpty()) {
                                                onEvent(
                                                    ChatCompletionEvent.GetResponse(
                                                        message = userInput,
                                                        accountType = currentCRM.actualPathName()
                                                    )
                                                )
                                                userInput = ""
                                            }
                                            true
                                        }

                                        else -> {
                                            false
                                        }
                                    }
                                },
                            textStyle = TextStyle(
                                fontFamily = PoppinsFont(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
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
                            onEvent(
                                ChatCompletionEvent.GetResponse(
                                    message = userInput,
                                    accountType = AccountType.POWERAPPS.toString().actualPathName()
                                )
                            )
                            userInput = ""
                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(
                            Icons.Rounded.Send,
                            ":",
                            modifier = Modifier
                                .size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DesktopChatScreen(
    chatUiState: ChatUiState,
    conversations: List<Message>,
    onEvent: (ChatCompletionEvent) -> Unit
) {

    var userInput by remember {
        mutableStateOf("")
    }

    var currentCRM by remember {
        mutableStateOf(AccountType.SALESFORCE.name.actualPathName())
    }

    var showMenu by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(userInput) {
        if (showMenu) {
            showMenu = false
        }
    }

    var bottomHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    val listState = rememberLazyListState()

    LaunchedEffect(conversations) {
        if (conversations.isNotEmpty()) {
            listState.animateScrollToItem(conversations.size)
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                        ) {
                            Image(
                                painter = if (currentCRM == AccountType.SALESFORCE.name.actualPathName()) {
                                    painterResource(Res.drawable.sales)
                                } else {
                                    painterResource(Res.drawable.power)
                                },
                                contentDescription = "",
                                modifier = Modifier
                                    .size(
                                        25.dp
                                    ),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                currentCRM,
                                fontFamily = PoppinsFont(),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp
                                    )
                            )
                        }
                    },
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .padding(
                                    end = 30.dp
                                )
                                .clip(CircleShape)
                                .background(
                                    MaterialTheme.colorScheme.surfaceContainerHigh
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 5.dp
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Black
                            )
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Black
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                showMenu = !showMenu
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                )
                AnimatedVisibility(
                    visible = chatUiState.isResponding,
                    enter = slideInVertically() + expandVertically(),
                    exit = slideOutVertically() + shrinkVertically()
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.Black,
                        trackColor = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = showMenu,
                enter = slideInHorizontally() + expandHorizontally(),
                exit = slideOutHorizontally() + shrinkHorizontally()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.3f)
                        .fillMaxHeight()
                        .clip(
                            RoundedCornerShape(
                                topStart = 0.dp,
                                bottomStart = 0.dp,
                                topEnd = 15.dp,
                                bottomEnd = 15.dp
                            )
                        )
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(
                            all = 20.dp
                        ),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .clickable {
                                currentCRM =
                                    if (currentCRM == AccountType.SALESFORCE.name.actualPathName()) {
                                        AccountType.POWERAPPS.name.actualPathName()
                                    } else {
                                        AccountType.SALESFORCE.name.actualPathName()
                                    }
                                showMenu = false
                                onEvent(ChatCompletionEvent.ClearConversation)
                            }
                            .padding(
                                all = 10.dp
                            ),
                        color = MaterialTheme.colorScheme.primary,
                        text = "Currently configured as a $currentCRM, Tap here to switch",
                        fontFamily = PoppinsFont(),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        "Features",
                        fontFamily = PoppinsFont(),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(
                                top = 20.dp
                            )
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 15.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Transcribe,
                            contentDescription = "",
                            modifier = Modifier
                                .size(
                                    18.dp
                                )
                        )
                        Text(
                            text = "Live Transcribe",
                            modifier = Modifier
                                .padding(
                                    start = 10.dp
                                ),
                            fontFamily = PoppinsFont(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            letterSpacing = 0.3.sp
                        )
                    }

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (conversations.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(
                                Res.drawable.logo
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(
                                    80.dp
                                )
                                .height(
                                    80.dp
                                )
                        )
                        Text(
                            text = "Welcome to InfoCRM",
                            fontFamily = PoppinsFont(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Send a hi to start the conversation with our $currentCRM",
                            fontFamily = PoppinsFont(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp
                                )
                                .alpha(.6f)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                bottom = bottomHeight
                            )
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(
                                horizontal = 160.dp
                            ),
                        state = listState
                    ) {
                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                            )
                        }
                        items(
                            conversations
                        ) {
                            if (it.role == "user") {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        it.content,
                                        fontFamily = PoppinsFont(),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
                                        modifier = Modifier
                                            .padding(
                                                bottom = 20.dp
                                            )
                                            .clip(CircleShape)
                                            .background(
                                                MaterialTheme.colorScheme.surfaceContainer
                                            )
                                            .padding(
                                                horizontal = 10.dp,
                                                vertical = 5.dp
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
                                            currentCRM,
                                            fontFamily = PoppinsFont(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .padding(
                                                    start = 10.dp
                                                )
                                        )
                                        Text(
                                            it.content,
                                            fontFamily = PoppinsFont(),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .padding(
                                                    start = 10.dp
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .onGloballyPositioned { placed ->
                            with(density) {
                                bottomHeight = placed.size.height.toDp() + 10.dp
                            }
                        }
                        .padding(
                            bottom = 20.dp
                        )
                        .padding(
                            horizontal = 130.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(
                            Icons.Rounded.Add,
                            ":",
                            modifier = Modifier
                                .size(18.dp)
                        )
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
                                .animateContentSize()
                                .onPreviewKeyEvent { keyEvent ->
                                    when {
                                        ((keyEvent.key == Key.ShiftLeft) || (keyEvent.key == Key.ShiftLeft)) && (keyEvent.key == Key.Enter) -> {
                                            true
                                        }

                                        keyEvent.key == Key.Enter -> {
                                            if (userInput.isNotEmpty()) {
                                                onEvent(
                                                    ChatCompletionEvent.GetResponse(
                                                        message = userInput,
                                                        accountType = currentCRM.actualPathName()
                                                    )
                                                )
                                                userInput = ""
                                            }
                                            true
                                        }

                                        else -> {
                                            false
                                        }
                                    }
                                },
                            textStyle = TextStyle(
                                fontFamily = PoppinsFont(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
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
                            onEvent(
                                ChatCompletionEvent.GetResponse(
                                    message = userInput,
                                    accountType = AccountType.POWERAPPS.toString().actualPathName()
                                )
                            )
                            userInput = ""
                        },
                        modifier = Modifier
                            .weight(.1f)
                    ) {
                        Icon(
                            Icons.Rounded.Send,
                            ":",
                            modifier = Modifier
                                .size(18.dp)
                        )
                    }
                }
            }
        }
    }
}