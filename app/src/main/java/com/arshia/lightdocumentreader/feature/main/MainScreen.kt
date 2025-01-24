package com.arshia.lightdocumentreader.feature.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val documents by viewModel.documents.collectAsStateWithLifecycle()
    var showPermissionRequest by viewModel.showPermissionDialog
    val requestLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
        onResult = { showPermissionRequest = false }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { ip ->
        LazyColumn(
            modifier = Modifier
                .padding(ip)
                .fillMaxSize(),
        ) {
            items(documents) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(it.title)
                }
            }
        }
        if (showPermissionRequest) {
            RequestDialog(
                onConfirm = {
//                    requestLauncher.launch(viewModel.readExternalStoragePermission.value)
                    viewModel.requestPermission()
                },
                onDismiss = { showPermissionRequest = false },
                text = "Access to External storage is needed to view device documents.",
                buttonText = "accept",
            )
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
