package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arshia.lightdocumentreader.feature.main.navigation.mainScreenNavigation
import com.arshia.lightdocumentreader.feature.viewer.navigation.deepLinkViewerScreenNavigation
import com.arshia.lightdocumentreader.feature.viewer.navigation.viewerScreenNavigation

@Composable
fun LDRNavHost(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { ip ->
        NavHost(
            startDestination = LDRRoutes.MainRoute,
            navController = navController,
            modifier = Modifier
                .padding(ip)
                .fillMaxSize(),
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
}
