package com.arshia.lightdocumentreader.app.navigation

import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable

@Serializable
sealed class LdrRoutes {

    @Serializable
    data object Main

    @Serializable
    data object Settings

    @Serializable
    data class Viewer(private var _uriString: String) {
        val uri: Uri
            get() = _uriString.toUri()
    }

    @Serializable
    data object DeepLinkViewer

}
