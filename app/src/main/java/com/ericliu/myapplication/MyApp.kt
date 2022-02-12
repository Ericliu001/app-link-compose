package com.ericliu.myapplication

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
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