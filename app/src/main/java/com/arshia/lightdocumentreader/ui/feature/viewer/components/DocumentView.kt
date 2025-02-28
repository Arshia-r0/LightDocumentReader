package com.arshia.lightdocumentreader.ui.feature.viewer.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.Bitmap
import coil3.compose.AsyncImage
import kotlin.math.roundToInt

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
            .gestureHandler(
                onDrag = {
                    setOffset(offset + it)
                },
                onPinch = { zoom, pan ->
                    setScale((scale * zoom).coerceIn(zoomRange))
                    setOffset(offset + pan)
                },
            )
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
            .scale(scale)
            .offset {
                IntOffset(
                    x = offset.x.roundToInt(),
                    y = offset.y.roundToInt(),
                )
            }
    ) {
        LazyColumn(
            state = lazyColumnState,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            userScrollEnabled = false,
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

fun Modifier.gestureHandler(
    onDrag: (Offset) -> Unit,
    onPinch: (zoom: Float, pan: Offset) -> Unit,
) = pointerInput(Unit) {
    awaitPointerEventScope {
        var pointersDown = setOf<PointerId>()
        var previousPositionsMap = mapOf<PointerId, Offset>()
        var previousDistance = 0f

        while (true) {
            val event = awaitPointerEvent()

            event.changes.forEach { change ->
                if (change.pressed) {
                    pointersDown += change.id
                    previousPositionsMap += change.id to change.position
                } else {
                    pointersDown -= change.id
                    previousPositionsMap -= change.id
                }
            }

            when (pointersDown.size) {
                1 -> {
                    val id = pointersDown.first()
                    val change = event.changes.first { it.id == id }
                    val currentPosition = change.position
                    var previousPosition = previousPositionsMap[id]
                    if (previousPosition != null && change.positionChanged()) {
                        val dragAmount = currentPosition - previousPosition
                        onDrag(dragAmount)
                    }
                    previousPositionsMap += id to currentPosition
                }

                2 -> {
                    val ids = pointersDown.first() to pointersDown.last()
                    val position1 = event.changes.first { it.id == ids.first }.position
                    val position2 = event.changes.first { it.id == ids.second }.position
                    val currentDistance = (position1 - position2).getDistance()

                    val previousPosition1 = previousPositionsMap[ids.first]
                    val previousPosition2 = previousPositionsMap[ids.second]

                    if (previousPosition1 != null && previousPosition2 != null) {
                        if (previousDistance > 0f) {
                            val zoom = currentDistance / previousDistance
                            val pan =
                                (position1 - previousPosition1 + position2 - previousPosition2) / 2f

                            onPinch(zoom, pan)
                        }
                    }
                    previousDistance = currentDistance
                    previousPositionsMap =
                        previousPositionsMap + (ids.first to position1) + (ids.second to position2)
                }

                else -> previousDistance = 0f
            }
        }
    }
}

