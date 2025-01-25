package com.arshia.lightdocumentreader.feature.viewer.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.arshia.lightdocumentreader.app.navigation.LDRNavTypes
import com.arshia.lightdocumentreader.app.navigation.LDRRoutes
import com.arshia.lightdocumentreader.feature.viewer.ViewerScreen
import kotlin.reflect.typeOf

fun NavGraphBuilder.viewerScreenNavigation(
    navigateBack: () -> Unit,
) {
    composable<LDRRoutes.ViewerRoute>(
        typeMap = mapOf(
            typeOf<Uri>() to LDRNavTypes.uriType
        )
    ) {
        ViewerScreen(
            uri = it.toRoute<LDRRoutes.ViewerRoute>().uri,
            navigateBack = navigateBack
        )
    }
}
