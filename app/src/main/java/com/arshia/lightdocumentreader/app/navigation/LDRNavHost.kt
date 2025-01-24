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
            mainScreenNavigation()
        }
    }
}
