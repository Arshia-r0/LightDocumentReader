package com.arshia.lightdocumentreader.ui.viewer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.scrollBy
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.Bitmap
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BitmapColumn(
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
    var lazyColumnSize by remember { mutableStateOf(IntSize.Zero) }
    val isZooming by remember { mutableStateOf(false) }
    val isDragging by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(ip)
            .pointerInput(isZooming) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    setScale(
                        (scale * zoom).coerceIn(zoomRange)
                    )

                    val maxX = ((scale - 1) * lazyColumnSize.width / 2).coerceAtLeast(0f)
                    setOffset(
                        offset.copy(
                            x = (offset + pan).x.coerceIn(-maxX, maxX)
                        )
                    )

                    lazyColumnState.apply {
                        scope.launch {
                            scrollBy(pan.y)
                        }
                    }
                }
            }
            .pointerInput(isDragging, isZooming) {
                detectDragGestures { _, dragAmount ->
                    val maxX = ((scale - 1) * lazyColumnSize.width / 2).coerceAtLeast(0f)
                    setOffset(
                        offset.copy(
                            x = (offset + dragAmount).x.coerceIn(-maxX, maxX)
                        )
                    )

                    lazyColumnState.apply {
                        scope.launch {
                            scrollBy(dragAmount.y)
                        }
                    }
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
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    lazyColumnSize = it.size
                },
            state = lazyColumnState,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            userScrollEnabled = false,
        ) {
            items(renderedPages) { page ->
                PdfPage(page = page)
            }
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

//.pointerInput(isDragging, isZooming) {
//    detectHorizontalDragGestures { _, dragAmount ->
//        println("horizontal drag: dragAmount=$dragAmount")
//        val maxX = ((scale - 1) * lazyColumnSize.width / 2).coerceAtLeast(0f)
//        setOffset(
//            offset.copy(
//                x = (offset.x + dragAmount).coerceIn(-maxX, maxX)
//            )
//        )
//    }
//}
//.combinedClickable(
//interactionSource = interactionSource,
//indication = null,
//onClick = {
//    
//},
//onDoubleClick = {
//    if (scale != 1f) {
//        setScale(1f)
//        setOffset(Offset.Zero)
//    } else setScale(3f)
//},
//)
//.graphicsLayer {
//    scaleX = scale
//    scaleY = scale
//    translationX = offset.x
//    translationY = offset.y
//},
