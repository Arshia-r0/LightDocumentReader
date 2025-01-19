package com.arshia.lightdocumentreader.core.model

import android.net.Uri

data class Document(
    val id: Long,
    val title: String,
    val uri: Uri,
//    val type: FileType,
    val size: Int,
)
