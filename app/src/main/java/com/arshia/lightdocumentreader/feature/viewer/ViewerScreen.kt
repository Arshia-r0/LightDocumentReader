package com.arshia.lightdocumentreader.feature.viewer

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arshia.lightdocumentreader.feature.viewer.components.PdfPage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ViewerScreen(
    uri: Uri,
    navigateBack: () -> Unit,
    viewModel: ViewerScreenViewModel = koinViewModel(parameters = { parametersOf(uri) }),
) {
    val uiState by viewModel.uiState
    val renderedPages by viewModel.renderedPages
    if (uiState is ViewerScreenUiState.Loading) {
        CircularProgressIndicator()
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
