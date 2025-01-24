package com.arshia.lightdocumentreader.feature.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    var pdfUri by viewModel.pdfUri
    val renderedPages by viewModel.renderedPages
    val choosePdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { pdfUri = it }
    LaunchedEffect(pdfUri) {
        viewModel.renderPdf()
    }
//    var showPermissionRequest by viewModel.showPermissionDialog
//    val requestLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission(),
//        onResult = { showPermissionRequest = false }
//    )
    if (pdfUri == null) {
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
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                items(renderedPages) { page ->
                    PdfPage(page = page)
                }
            }
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
