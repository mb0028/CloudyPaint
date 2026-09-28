package mb28.cloudyPaint.core

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import mb28.cloudyPaint.CanvasActivity

fun createNewProject(context: Activity, name: String, w: Int, h: Int) {
    try {
        context.startActivity(Intent(context, CanvasActivity::class.java))
    } catch (e: Exception) {
        Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}


