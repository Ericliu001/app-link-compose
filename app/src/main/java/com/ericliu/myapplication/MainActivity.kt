package com.ericliu.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        Column {
            MakeTitle(message = "Hello")
            MakeTitle(message = message.value)

            Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text(text = "Click Me")
                }
            }
        }
    }
}

@Composable
fun MakeTitle(message: String) {
    Text(
        text = message,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
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