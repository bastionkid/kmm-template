package com.azuredragon.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azuredragon.app.Greeting
import com.azuredragon.app.SharedRes
import com.azuredragon.app.StringRes
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                GreetingView()
            }
        }
    }
}

@Composable
fun GreetingView() {
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
                val context = LocalContext.current

                Text(Greeting().greet())

                Spacer(modifier = Modifier.size(16.dp))

                Text(StringRes(context).getString(SharedRes.strings.label_name))

                Spacer(modifier = Modifier.size(16.dp))

                Text(StringRes(context).getString(SharedRes.strings.label_name_with_args, listOf("Args")))

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = "Red Text",
                    color = colorResource(SharedRes.colors.red),
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(StringRes(context).getPluralString(SharedRes.plurals.quantity, 0))
                Text(StringRes(context).getPluralString(SharedRes.plurals.quantity, 1))
                Text(StringRes(context).getPluralString(SharedRes.plurals.quantity, 2))

                Spacer(modifier = Modifier.size(16.dp))

                Text(StringRes(context).getPluralString(SharedRes.plurals.fruit_quantity, 0, listOf(0, "Apple")))
                Text(StringRes(context).getPluralString(SharedRes.plurals.fruit_quantity, 1, listOf(1, "Apple")))
                Text(StringRes(context).getPluralString(SharedRes.plurals.fruit_quantity, 2, listOf(2, "Apple")))

                Spacer(modifier = Modifier.size(16.dp))

                Image(
                    painter = painterResource(imageResource = SharedRes.images.taj_mahal),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView()
    }
}
