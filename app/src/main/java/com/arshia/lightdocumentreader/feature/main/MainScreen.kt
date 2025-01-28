package com.arshia.lightdocumentreader.feature.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    toViewerScreen: (Uri) -> Unit,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val choosePdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { it?.let { toViewerScreen(it) } }
//    var showPermissionRequest by viewModel.showPermissionDialog
//    val requestLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission(),
//        onResult = { showPermissionRequest = false }
//    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { choosePdfLauncher.launch("application/pdf") }
        ) {
            Text("choose pdf")
        }
    }
}


@Composable
private fun RequestDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    text: String,
    buttonText: String
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(buttonText)
            }
        },
        title = {
            Text(
                text = "Request permission",
                fontSize = 20.sp
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 15.sp
            )
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        }
    )
}
