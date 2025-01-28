package com.arshia.lightdocumentreader.feature.viewer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.arshia.lightdocumentreader.app.navigation.LDRRoutes
import com.arshia.lightdocumentreader.feature.viewer.ViewerScreen

fun NavGraphBuilder.viewerScreenNavigation(
    navigateBack: () -> Unit,
) {
    composable<LDRRoutes.ViewerRoute>(
        deepLinks = listOf(
            navDeepLink<LDRRoutes.ViewerRoute>("content://")
        )
    ) {
        println(it.toRoute<LDRRoutes.ViewerRoute>().uri)
        ViewerScreen(
            uri = it.toRoute<LDRRoutes.ViewerRoute>().uri,
            navigateBack = navigateBack
        )
    }
}
