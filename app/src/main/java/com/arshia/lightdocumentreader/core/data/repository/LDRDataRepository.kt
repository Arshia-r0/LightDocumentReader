package com.arshia.lightdocumentreader.core.data.repository

import com.arshia.lightdocumentreader.core.designsystem.theme.AppTheme
import com.arshia.lightdocumentreader.core.model.LDRData
import kotlinx.coroutines.flow.Flow

interface LDRDataRepository {

    val ldrData: Flow<LDRData>

    suspend fun setAppTheme(appTheme: AppTheme)

}
