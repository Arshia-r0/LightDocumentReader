package com.arshia.lightdocumentreader.feature.viewer.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.Bitmap
import coil3.compose.AsyncImage

@Composable
fun PdfPage(
    modifier: Modifier = Modifier,
    page: Bitmap,
) {
    AsyncImage(
        model = page,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(page.width.toFloat() / page.height.toFloat())
    )
}
