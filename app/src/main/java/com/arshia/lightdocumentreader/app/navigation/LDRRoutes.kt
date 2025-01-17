package com.arshia.lightdocumentreader.app.navigation

import kotlinx.serialization.Serializable

sealed interface LDRRoutes {

    @Serializable
    object MainRoute

    @Serializable
    object SettingsRoute

    @Serializable
    object ViewerRoute

}
