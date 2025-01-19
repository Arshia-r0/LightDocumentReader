package com.arshia.lightdocumentreader.core.data.repository.imp

import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import com.arshia.lightdocumentreader.core.datastore.LDRDataStore
import com.arshia.lightdocumentreader.core.designsystem.theme.AppTheme
import com.arshia.lightdocumentreader.core.model.LDRData
import kotlinx.coroutines.flow.map

class LDRDataRepositoryImpl(
    private val dataStore: LDRDataStore,
) : LDRDataRepository {

    override val ldrData = dataStore.ldrData.map {
        LDRData(it.appTheme)
    }

    override suspend fun setAppTheme(appTheme: AppTheme) {
        dataStore.setAppTheme(appTheme)
    }

}
