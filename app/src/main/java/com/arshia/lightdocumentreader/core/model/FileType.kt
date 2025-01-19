package com.arshia.lightdocumentreader.core.model

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase

enum class FileType() {
    PDF;

    override fun toString(): String {
        return super.toString().toLowerCase(Locale.current)
    }

}
