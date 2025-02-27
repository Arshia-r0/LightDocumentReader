package com.arshia.lightdocumentreader.ui.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LdrScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    blockUiLoading: Boolean,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
    ) { ip ->
        content(ip)
    }
    if (blockUiLoading) LdrLoadingIndicator()
}
