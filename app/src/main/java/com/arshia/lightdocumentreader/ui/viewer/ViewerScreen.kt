package com.arshia.lightdocumentreader.ui.viewer

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.arshia.lightdocumentreader.ui.viewer.components.PdfPage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewerScreen(
    uri: Uri,
    navigateBack: () -> Unit,
    zoomRange: ClosedFloatingPointRange<Float> = 0.5f..3f,
    viewModel: ViewerScreenViewModel = koinViewModel(parameters = { parametersOf(uri) }),
) {
    val loading by viewModel.loading
    val renderedPages by viewModel.renderedPages
    var scale by viewModel.scale
    var offset by viewModel.offset
    val transformableState = rememberTransformableState { zoom, pan, _ ->
        scale = (scale * zoom).coerceIn(zoomRange)
        offset += pan
    }
   
    if (loading) LoadingDocument()
    else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(zoomRange)
                        offset += pan
                    }
                }
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        // todo view toolbar
                    },
                    onDoubleClick = {
                        if (scale != 1f) {
                            scale = 1f
                            offset = Offset.Zero
                        } else scale = 3f
                    }
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
                    .transformable(transformableState),
            ) {
                items(renderedPages) { page ->
                    PdfPage(page = page)
                }
            }
        }
    }
}

@Composable
fun LoadingDocument() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
