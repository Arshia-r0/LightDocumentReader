package com.arshia.lightdocumentreader.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LDRNavHost(
    ip: PaddingValues,
    navController: NavController = rememberNavController(),
) {
//    NavHost(
//        modifier = Modifier
//            .padding(ip)
//            .fillMaxSize(),
//        navController = navController,
//        startDestination = LDRRoutes.MainRoute
//    ) {
//    }
}
