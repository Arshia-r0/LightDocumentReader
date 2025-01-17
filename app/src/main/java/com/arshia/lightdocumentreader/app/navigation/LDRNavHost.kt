package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun LDRNavHost(
    ip: PaddingValues,
    navController: NavController = rememberNavController(),
) {
    NavHost(
        modifier = Modifier
            .padding(ip)
            .fillMaxSize(),
        navController = navController,
        startDestination = LDRRoutes.MainRoute
    ) {
    }
}
