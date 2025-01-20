package com.arshia.lightdocumentreader.app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class LDRRoutes {

    @Serializable
    data object MainRoute

    @Serializable
    data object SettingsRoute

    @Serializable
    data object ViewerRoute

}
