package com.arshia.lightdocumentreader.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arshia.lightdocumentreader.app.navigation.LDRRoutes
import com.arshia.lightdocumentreader.feature.main.MainScreen

fun NavGraphBuilder.mainScreenNavigation() {
    composable<LDRRoutes.MainRoute> {
        MainScreen()
    }
}
