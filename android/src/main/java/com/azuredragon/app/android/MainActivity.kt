package com.azuredragon.app.android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.azuredragon.app.Greeting
import com.azuredragon.app.SharedRes
import com.azuredragon.app.StringRes
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.compose.colorResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .align(Alignment.Center),
                        ) {
                            GreetingView(Greeting().greet())

                            Spacer(modifier = Modifier.size(16.dp))

                            GreetingView(StringRes(this@MainActivity).getString(SharedRes.strings.label_name))

                            Spacer(modifier = Modifier.size(16.dp))

                            GreetingView(StringRes(this@MainActivity).getString(SharedRes.strings.label_name_with_args, listOf("Args")))

                            Spacer(modifier = Modifier.size(16.dp))

                            Text(
                                text = "Red Text",
                                color = colorResource(SharedRes.colors.red),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
