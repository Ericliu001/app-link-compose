package com.ericliu.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ericliu.myapplication.ui.theme.AppLinkApplicationTheme

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
                    Greeting(greetingMessage)
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
fun Greeting(message: MutableState<String>) {
    Text(text = "Hello ${message.value}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppLinkApplicationTheme {
        Greeting(remember {
            mutableStateOf("Android")
        })
    }
}