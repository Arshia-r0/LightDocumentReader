package com.arshia.lightdocumentreader.ui.viewer

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.arshia.lightdocumentreader.core.designsystem.components.LDRLoadingIndicator
import com.arshia.lightdocumentreader.ui.viewer.components.BitmapColumn
import com.arshia.lightdocumentreader.ui.viewer.components.ViewerTopBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var lazyColumnSize by remember { mutableStateOf(IntSize.Zero) }
    val transformableState = rememberTransformableState { zoom, pan, _ ->
        scale = (scale * zoom).coerceIn(zoomRange)
    }

    Scaffold(
        topBar = {
            ViewerTopBar(
                navigateBack = navigateBack,
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { ip ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(ip)
                .transformable(transformableState)
                .globalZoom(
                    scale = scale,
                    setScale = { scale = it },
                    zoomRange = zoomRange,
                    lazyColumnSize = lazyColumnSize,
                    setLazyColumnSize = { lazyColumnSize = it },
                    offset = offset,
                    setOffset = { offset = it },
                    interactionSource = remember { MutableInteractionSource() },
                    scrollBehavior = scrollBehavior,
                ),
        ) {
            BitmapColumn(renderedPages = renderedPages)
        }
        if (loading) LDRLoadingIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
fun Modifier.globalZoom(
    scale: Float,
    setScale: (Float) -> Unit,
    setOffset: (Offset) -> Unit,
    lazyColumnSize: IntSize,
    setLazyColumnSize: (IntSize) -> Unit,
    offset: Offset,
    zoomRange: ClosedFloatingPointRange<Float>,
    interactionSource: MutableInteractionSource?,
    scrollBehavior: TopAppBarScrollBehavior,
) = this
    .pointerInput(Unit) {
        detectTransformGestures { centroid, pan, zoom, _ ->
            setScale((scale * zoom).coerceIn(zoomRange))

            val maxX = ((scale - 1) * lazyColumnSize.width / 2).coerceAtLeast(0f)
            val maxY = ((scale - 1) * lazyColumnSize.height / 2).coerceAtLeast(0f)

            val newOffset = offset + pan
            setOffset(
                Offset(
                    x = newOffset.x.coerceIn(-maxX, maxX),
                    y = newOffset.y.coerceIn(-maxY, maxY)
                )
            )
        }
        detectDragGestures { _, dragAmount ->
            val maxX = ((scale - 1) * lazyColumnSize.width / 2).coerceAtLeast(0f)
            val maxY = ((scale - 1) * lazyColumnSize.height / 2).coerceAtLeast(0f)

            val newOffset = offset + dragAmount
            setOffset(
                Offset(
                    x = newOffset.x.coerceIn(-maxX, maxX),
                    y = newOffset.y.coerceIn(-maxY, maxY)
                )
            )
        }
//                    detectVerticalDragGestures()
//                    detectHorizontalDragGestures()
//                    detectDragGesturesAfterLongPress()
//                    detectTapGestures()
    }
    .combinedClickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = {
            scrollBehavior.state.heightOffset =
                if (scrollBehavior.state.heightOffset != 0f) 0f
                else -Float.MAX_VALUE
        },
        onDoubleClick = {
            if (scale != 1f) {
                setScale(1f)
                setOffset(Offset.Zero)
            } else setScale(3f)
        }
    )
    .graphicsLayer {
        scaleX = scale
        scaleY = scale
        translationX = offset.x
        translationY = offset.y
    }
    .onGloballyPositioned {
        setLazyColumnSize(it.size)
    }
