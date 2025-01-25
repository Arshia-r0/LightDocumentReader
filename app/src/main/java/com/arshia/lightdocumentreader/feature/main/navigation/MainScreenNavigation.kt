package com.arshia.lightdocumentreader.feature.main.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arshia.lightdocumentreader.app.navigation.LDRRoutes
import com.arshia.lightdocumentreader.feature.main.MainScreen

fun NavGraphBuilder.mainScreenNavigation(
    toViewerScreen: (Uri) -> Unit
) {
    composable<LDRRoutes.MainRoute> {
        MainScreen(
            toViewerScreen = toViewerScreen
        )
    }
}
