package com.arshia.lightdocumentreader.ui.viewer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.Bitmap
import coil3.compose.AsyncImage

@Composable
fun BoxWithConstraintsScope.BitmapColumn(renderedPages: List<Bitmap>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxHeight),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(renderedPages) { page ->
            PdfPage(page = page)
        }
    }
}

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
