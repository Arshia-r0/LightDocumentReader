package com.arshia.lightdocumentreader.ui.feature.main.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arshia.lightdocumentreader.app.navigation.LdrRoutes
import com.arshia.lightdocumentreader.ui.feature.main.MainScreen

fun NavGraphBuilder.mainScreenNavigation(
    toViewerScreen: (Uri) -> Unit
) {
    composable<LdrRoutes.Main> {
        MainScreen(
            toViewerScreen = toViewerScreen
        )
    }
}
