package com.ericliu.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ericliu.myapplication.ui.theme.AppLinkApplicationTheme

/**
 * Launch from https://ericliu001.github.io/user/eric
 */
class MainActivity : ComponentActivity() {
    private var greetingMessage = mutableStateOf("Android")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppLinkApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(greetingMessage)

                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = "Good Morning!", style = MaterialTheme.typography.body1)
                    }
                }
            }
        }

        handleIntent()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent()
    }

    private fun handleIntent() {
        intent.data?.let {
            intent.data?.lastPathSegment?.let {
                greetingMessage.value = it
            }
        }
    }
}

@Composable
fun MyApp(message: MutableState<String>) {
    Surface(color = MaterialTheme.colors.primary) {
        val clicked = remember { mutableStateOf(false) }

        Column {
            MakeTitle(message = "Hello")
            MakeTitle(message = message.value, clicked.value)

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = {
                        clicked.value = !clicked.value
                    },
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text(text = if (clicked.value) "I'm Clicked!" else "Click Me.")

                }
            }
        }
    }
}

@Composable
fun MakeTitle(message: String, expanded: Boolean = false) {
    var modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()

    val fontSize by animateIntAsState(targetValue = if (expanded) 45 else 25)
    val enlargedSize by animateDpAsState(targetValue = 80.dp)

    if (expanded) {
        modifier = modifier.height(height = enlargedSize)
    } else {
        modifier = modifier.wrapContentHeight()
    }

    Text(
        text = message,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppLinkApplicationTheme {
        MyApp(remember {
            mutableStateOf("Android")
        })
    }
}