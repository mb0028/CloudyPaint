package mb28.cloudyPaint.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import mb28.cloudyPaint.core.createNewProject

@Composable
fun NewPopup(onDismiss: () -> Unit) {
    val context = LocalActivity.current!!
    var name by remember { mutableStateOf("") }
    var w by remember { mutableStateOf("600") }
    var h by remember { mutableStateOf("800") }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton =  {
            Button(
                {
                    createNewProject(context, name, w.toInt(), h.toInt())
                    onDismiss()
                },
                enabled = !(w.toIntOrNull() == null || h.toIntOrNull() == null || name.isBlank())
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            OutlinedButton(
                { onDismiss() }
            ) {
                Text("Cancel")
            }
        },
        title = {
            Text("New Project")
        },
        text = {
            Column {
                OutlinedTextField(
                    name,
                    { name = it },
                    label = {
                        Text("Name")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    shape = RoundedCornerShape(15.dp),
                    isError = name.isBlank(),
                )
                Spacer(Modifier.height(5.dp))
                Row {
                    OutlinedTextField(
                        w,
                        { w = it },
                        label = {
                            Text("Width")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberSigned
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth(0.48f),
                        isError = w.toIntOrNull() == null,
                    )
                    Spacer(Modifier.fillMaxWidth(0.05f))
                    OutlinedTextField(
                        h,
                        { h = it },
                        label = {
                            Text("Height")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberSigned
                        ),
                        shape = RoundedCornerShape(15.dp),
                        isError = h.toIntOrNull() == null,
                    )
                }
            }

        }
    )
}
