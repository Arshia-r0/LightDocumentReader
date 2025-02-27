package com.arshia.lightdocumentreader.ui.feature.viewer

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.arshia.lightdocumentreader.ui.designsystem.components.LdrScaffold
import com.arshia.lightdocumentreader.ui.feature.viewer.components.DocumentView
import com.arshia.lightdocumentreader.ui.feature.viewer.components.ViewerTopBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ViewerScreen(
    uri: Uri,
    navigateBack: () -> Unit,
    viewModel: ViewerScreenViewModel = koinViewModel(parameters = { parametersOf(uri) }),
) {
    val loading by viewModel.loading
    val renderedPages by viewModel.renderedPages
    var scale by viewModel.scale
    var offset by viewModel.offset
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LdrScaffold(
        topBar = {
            ViewerTopBar(
                navigateBack = navigateBack,
                scrollBehavior = scrollBehavior,
            )
        },
        blockUiLoading = loading,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { ip ->
        DocumentView(
            ip = ip,
            renderedPages = renderedPages,
            scale = scale,
            offset = offset,
            setScale = { scale = it },
            setOffset = { offset = it },
        )
    }
}
