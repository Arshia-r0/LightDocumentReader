package com.arshia.lightdocumentreader.core.datastore

import com.arshia.lightdocumentreader.ui.designsystem.theme.AppTheme
import kotlinx.serialization.Serializable

@Serializable
data class LDRDataStoreModel(
    val appTheme: AppTheme = AppTheme.System,
)
