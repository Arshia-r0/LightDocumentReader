package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arshia.lightdocumentreader.ui.feature.main.navigation.mainScreenNavigation
import com.arshia.lightdocumentreader.ui.feature.viewer.navigation.viewerScreenNavigation

@Composable
fun LdrNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        startDestination = LdrRoutes.Main,
        navController = navController,
    ) {
        mainScreenNavigation(
            toViewerScreen = { uri -> navController.navigate(LdrRoutes.Viewer(uri.toString())) }
        )
        viewerScreenNavigation(
            navigateBack = { navController.navigateUp() },
            deepLickNavigateBack = { navController.navigateAndPopBackStack(LdrRoutes.Main) }
        )
    }
}

fun NavHostController.navigateAndPopBackStack(destination: Any) {
    navigate(destination) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}
