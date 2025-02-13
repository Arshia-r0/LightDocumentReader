package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arshia.lightdocumentreader.ui.main.navigation.mainScreenNavigation
import com.arshia.lightdocumentreader.ui.viewer.navigation.deepLinkViewerScreenNavigation
import com.arshia.lightdocumentreader.ui.viewer.navigation.viewerScreenNavigation

@Composable
fun LDRNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        startDestination = LDRRoutes.MainRoute,
        navController = navController,
    ) {
        mainScreenNavigation(
            toViewerScreen = { uri -> navController.navigate(LDRRoutes.ViewerRoute(uri.toString())) }
        )
        viewerScreenNavigation(
            navigateBack = { navController.navigateUp() }
        )
        deepLinkViewerScreenNavigation(
            navigateBack = {
                navController.navigate(LDRRoutes.MainRoute)
            }
        )
    }
}
