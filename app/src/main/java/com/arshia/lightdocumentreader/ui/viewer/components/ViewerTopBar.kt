package com.arshia.lightdocumentreader.ui.viewer.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewerTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateBack: () -> Unit,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "back",
                )
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            SearchButton()
        }
    )
}

@Composable
fun SearchButton() {
    IconButton(
        onClick = { /* todo */ }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "search",
        )
    }
}
