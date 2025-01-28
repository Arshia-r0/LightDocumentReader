package com.arshia.lightdocumentreader.app.navigation

import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable

@Serializable
sealed class LDRRoutes {

    @Serializable
    data object MainRoute

    @Serializable
    data object SettingsRoute

    @Serializable
    data class ViewerRoute(private var _uriString: String) {
        val uri: Uri
            get() = _uriString.toUri()
    }

    @Serializable
    data object DeepLinkViewerRoute

}
