package com.arshia.lightdocumentreader.app.navigation

import android.net.Uri
import com.arshia.lightdocumentreader.core.model.serializer.UriSerializer
import kotlinx.serialization.Serializable

@Serializable
sealed class LDRRoutes {

    @Serializable
    data object MainRoute

    @Serializable
    data object SettingsRoute

    @Serializable(with = UriSerializer::class)
    data class ViewerRoute(val uri: Uri)

}
