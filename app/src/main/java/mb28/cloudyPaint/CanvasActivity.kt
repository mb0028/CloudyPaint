package mb28.cloudyPaint

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import mb28.cloudyPaint.ui.theme.CloudyPaintTheme

const val EXTRA_W = "image_width"
const val EXTRA_H = "image_height"
const val EXTRA_N = "image_name"

class CanvasActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false

        width = intent.getFloatExtra(EXTRA_W, 800f)
        height = intent.getFloatExtra(EXTRA_H, 800f)
        name = intent.getStringExtra(EXTRA_N) ?: "Test"
        bitmap = createBitmap(width.toInt(), height.toInt())

        setContent {
            CloudyPaintTheme {
                Scaffold(
                    Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            {
                                Text(debugDrag, fontSize = 16.sp)
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                Color.Transparent
                            ),
                            actions = {
                                IconButton(
                                    {
//                                        bitmap.applyCanvas {
//                                            val p = Paint()
//                                            p.color = android.graphics.Color.CYAN
//                                            paths.forEach { (i, path) ->
//                                                drawPath(
//                                                    path.asAndroidPath(),
//                                                    p
//                                                )
//                                            }
//                                        }
                                    }
                                ) {
                                    Text("Save")
                                }
                            }
                        )
                    }
                ) {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        CloudyCanvas()
                    }
                }
            }
        }
    }

    var debugDrag by mutableStateOf("...")

    lateinit var bitmap: Bitmap
    var name = ""
    var width = 600f
    var height = 800f

    var paths = mutableStateMapOf<Int, Path>()
    var currentPathI by mutableIntStateOf(0)
    var currentPath by mutableStateOf(Path())

    @Composable
    fun CloudyCanvas() {
        Canvas(
            Modifier
                .aspectRatio(width / height).clip(RectangleShape)
                .background(Color.White)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            currentPath = Path()
                            currentPath.moveTo(it.x, it.y)
                        },
                        onDragEnd = {
                            paths[currentPathI] = currentPath
                            currentPathI++
                        }
                    ) { change, dragAmount ->
                        debugDrag = "offset: ${change.position}\ndrag amount: $dragAmount" +
                                "\npath index = $currentPathI, paths count: ${paths.count()}"

                        val x = change.position.x
                        val y = change.position.y
                        currentPath.lineTo(x, y)
                    }
                }
        ) {
            paths.values.forEach {
                drawPath(it, Color.Black)
            }
        }
    }
}
