package com.arshia.lightdocumentreader.ui.feature.viewer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil3.Bitmap
import coil3.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DocumentView(
    ip: PaddingValues,
    renderedPages: List<Bitmap>,
    scale: Float,
    offset: Offset,
    zoomRange: ClosedFloatingPointRange<Float> = 0.5f..3f,
    setScale: (Float) -> Unit,
    setOffset: (Offset) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val lazyColumnState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(ip)
            .pointerInput(Unit) {
                awaitEachGesture {
                    val event = awaitPointerEvent()
                    // todo
                }
            }
            .combinedClickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = {
                    // todo
                },
                onDoubleClick = {
                    if (scale != 1f) {
                        setScale(1f)
                        setOffset(Offset.Zero)
                    } else setScale(3f)
                }
            ),
    ) {
        LazyColumn(
            state = lazyColumnState,
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(renderedPages) { page ->
                DocumentPage(page = page)
            }
        }
    }
}

@Composable
fun DocumentPage(
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
