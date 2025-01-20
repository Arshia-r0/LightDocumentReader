package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arshia.lightdocumentreader.feature.main.navigation.mainScreenNavigation

@Composable
fun LDRNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        startDestination = LDRRoutes.MainRoute,
        navController = navController,
        modifier = Modifier.fillMaxSize(),
    ) {
        mainScreenNavigation()
    }
}
