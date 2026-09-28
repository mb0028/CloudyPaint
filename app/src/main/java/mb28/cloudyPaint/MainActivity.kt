package mb28.cloudyPaint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mb28.cloudyPaint.ui.NewPopup
import mb28.cloudyPaint.ui.theme.CloudyPaintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false

        setContent {
            CloudyPaintTheme {
                var newPopupOpened by remember { mutableStateOf(false) }

                Scaffold(
                    Modifier.fillMaxSize(),
                    bottomBar = { },
                    topBar = {
                        TopAppBar(
                            { Text("Cloudy Paint") },
                            actions = {
                                IconButton(
                                    {  }
                                ) {
                                    Text("Open")
                                }
                                Spacer(Modifier.width(5.dp))
                                FilledTonalIconButton(
                                    { newPopupOpened = true }
                                ) {
                                    Text("New")
                                }
                            }
                        )
                    }
                ) { p -> p
                    Column(
                        Modifier.fillMaxSize(),
                        Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {
                    }
                }

                if (newPopupOpened) {
                    NewPopup { newPopupOpened = false }
                }
            }
        }
    }
}
