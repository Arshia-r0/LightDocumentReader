package com.arshia.lightdocumentreader.feature.viewer.navigation

import android.content.Intent
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.arshia.lightdocumentreader.app.navigation.LDRRoutes
import com.arshia.lightdocumentreader.feature.viewer.ViewerScreen

fun NavGraphBuilder.viewerScreenNavigation(
    navigateBack: () -> Unit,
) {
    composable<LDRRoutes.ViewerRoute> { backstackEntry ->
        ViewerScreen(
            uri = backstackEntry.toRoute<LDRRoutes.ViewerRoute>().uri,
            navigateBack = navigateBack
        )
    }
}

fun NavGraphBuilder.deepLinkViewerScreenNavigation(
    navigateBack: () -> Unit,
) {
    composable<LDRRoutes.DeepLinkViewerRoute>(
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "content://"
                action = Intent.ACTION_VIEW
                mimeType = "application/pdf"
            },
            navDeepLink {
                uriPattern = "content://"
                action = Intent.ACTION_OPEN_DOCUMENT
                mimeType = "application/pdf"
            }
        )
    ) { backstackEntry ->
        val uri =
            (backstackEntry.arguments?.get(NavController.KEY_DEEP_LINK_INTENT) as? Intent)?.data
        Log.d("deeplinkScreenNav", uri.toString())
        uri?.let {
            ViewerScreen(
                uri = uri,
                navigateBack = navigateBack
            )
        } ?: navigateBack
    }
}
