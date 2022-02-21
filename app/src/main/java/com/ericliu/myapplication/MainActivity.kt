package com.ericliu.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.ericliu.myapplication.link.GithubUriProcessor
import com.ericliu.myapplication.link.UriProcessorLocator
import com.ericliu.myapplication.ui.theme.AppLinkApplicationTheme

/**
 * Launch from https://ericliu001.github.io/user?name=eric&title=employee&age=5
 */
class MainActivity : ComponentActivity() {
    private val greetingMessage = mutableStateOf("Android")
    private lateinit var uriProcessorLocator: UriProcessorLocator

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

        uriProcessorLocator = UriProcessorLocator(listOf(GithubUriProcessor(greetingMessage)))

        handleIntent()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent()
    }

    private fun handleIntent() {
        intent.data?.let { uri ->
            val processor = uriProcessorLocator.getProcessor(uri)
            processor?.process(uri)
        }
    }
}

