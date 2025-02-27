package com.arshia.lightdocumentreader.core.data.repository

import com.arshia.lightdocumentreader.core.model.LdrData
import com.arshia.lightdocumentreader.ui.designsystem.theme.AppTheme
import kotlinx.coroutines.flow.Flow

interface LDRDataRepository {

    val ldrData: Flow<LdrData>

    suspend fun setAppTheme(appTheme: AppTheme)

}
