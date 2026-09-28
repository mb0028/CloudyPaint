package mb28.cloudyPaint.core

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import mb28.cloudyPaint.CanvasActivity
import mb28.cloudyPaint.EXTRA_H
import mb28.cloudyPaint.EXTRA_N
import mb28.cloudyPaint.EXTRA_W

fun createNewProject(context: Activity, name: String, w: Int, h: Int) {
    try {
        val intent = Intent(context, CanvasActivity::class.java)
            .putExtra(EXTRA_W, w.toFloat())
            .putExtra(EXTRA_H, h.toFloat())
            .putExtra(EXTRA_N, name)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}


